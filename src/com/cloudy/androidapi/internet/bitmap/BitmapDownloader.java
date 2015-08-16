package com.cloudy.androidapi.internet.bitmap;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.RejectedExecutionException;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.internet.bitmap.BitmapTransitionDrawable.BitmapTransitionCallback;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;


public class BitmapDownloader {

	private static final String TAG = BitmapDownloader.class.getCanonicalName();
    /**
     * DOWNLOAD_TAG
     */
	public static final int DOWNLOAD_TAG = R.id.bmd__image_downloader;
	/**
	 * Ĭ�ϵ������������
	 */
	private final int DEFAULT_DOWNLOAD_NUM = 5;
	/**
	 * ͼƬ����·��Ŀ¼
	 * �ļ�����ͨ��MD5 hash������
	 */
	private String DEFAULT_PATH_PIC = null;
	/**
	 * BitmapDownloaderʵ������,����ģʽ
	 */
	private static BitmapDownloader mBitmapDowloader = null;
	/**
	 * BitmapCache ͼƬ�ڴ滺��
	 */
	private final BitmapCache mBitmapCache = new BitmapCache();
	/**
	 * �����б�
	 */
	private ArrayList<Download> mQueuedDownloads;
	/**
	 * ���������е��б�
	 */
	private ArrayList<Download> mRunningDownloads;
	/**
	 * �ظ�������
	 */
	private HashMap<String, ArrayList<Download>> mDuplicateDownloads;
	/**
	 * ������ص�����
	 */
	private int mMaxDownloads = DEFAULT_DOWNLOAD_NUM;
	/**
	 * ���ش���ʱ��ʾ��ͼƬ
	 */
	private Drawable mErrorDrawable;
	/**
	 * ��������ʾ��ͼƬ
	 */
	private Drawable mInProgressDrawable;
	/**
	 * ���ش���ʱ��ͼƬ����ԴID
	 */
	private int mErrorDrawableResource;
	/**
	 * �����е�ͼƬ����ԴID
	 */
	private int mInProgressDrawableResource;
	/**
	 * �Ƿ���ʾ����
	 */
	private boolean mAnimateImageAppearance = false;
	/**
	 * �������ʱ�Ƿ���ʾ����
	 */
	private boolean mAnimateImageAppearanceAfterDownload = true;
	/**
	 * ������ɻص�
	 */
	private BitmapLoaderCallback mCallback;
	
	/**
	 * ����ö����
	 */
	public static enum AnimateAppearance {
		ANIMATE_ALWAYS, ANIMATE_AFTER_DOWNLOAD, ANIMATE_NEVER
	}

	/**
	 * ������ɻص��ӿ�
	 */
	public static interface BitmapLoaderCallback {
		public void onLoaded(ImageView view);
	}

	/**
	 * ���maxDownloadsС��0��BitmapDownloader�������������������ΪĬ�ϵ�5��.�����ʹ��maxDownloads�������,
	 * һ���������������ֵ���Ͳ������������ã����һ��Ҫ���ã��뽫mBitMapDowloader����Ϊ�գ�������ͨ��getInstance������ȡmBitMapDowloader
	 * @param maxDownloads �������ֵ
	 * @return BitmapDownloaderʵ��
	 */
	public static BitmapDownloader getInstance(int maxDownloads){
		if(mBitmapDowloader == null) {
			if(maxDownloads <= 0) {
				mBitmapDowloader = new BitmapDownloader();
			} else {
				mBitmapDowloader = new BitmapDownloader(maxDownloads);
			}
		}
		return mBitmapDowloader;
	}
	
	/**
	 * ����ģʽ��˽�й�����
	 */
	private BitmapDownloader() {
		setup(DEFAULT_DOWNLOAD_NUM);
	}

	/**
	 * ����ģʽ��˽�й�����
	 * @param maxDownloads ������صĸ���
	 */
	private BitmapDownloader(int maxDownloads) {
		setup(maxDownloads);
	}
	
	/**
	 * ��ʼ�������������������������
	 * @param maxDownloads �����������
	 */
	private void setup(int maxDownloads) {
		mQueuedDownloads = new ArrayList<Download>();
		mRunningDownloads = new ArrayList<Download>();
		mMaxDownloads = maxDownloads;
		mDuplicateDownloads = new HashMap<String, ArrayList<Download>>();
	}

	/**
	 * �������ش���ʱ��ʾ�� Drawable
	 * @param errorDrawable ���ش���ʱ��ʾ�� Drawable
	 */
	public void setErrorDrawable(Drawable errorDrawable) {
		mErrorDrawable = errorDrawable;
		mErrorDrawableResource = -1;
	}
	
	/**
	 * ����ͼƬ����·��
	 * @param path 
	 */
	public void setCachePath(String path){
		DEFAULT_PATH_PIC = path;
	}
	
	/**
	 * ������������ʾ��Drawable
	 * @param inProgressDrawable ��������ʾ��Drawable
	 */
	public void setInProgressDrawable(Drawable inProgressDrawable) {
		mInProgressDrawable = inProgressDrawable;
		mInProgressDrawableResource = -1;
	}

	/**
	 * �������ش���ʱ��ʾ��λͼ��ԴID
	 * @param errorDrawable ���ش���ʱ��ʾ��λͼ��ԴID
	 */
	public void setErrorDrawable(int errorDrawable) {
		mErrorDrawable = null;
		mErrorDrawableResource = errorDrawable;
	}

	/**
	 * ������������ʾ��λͼ����Դ��ID
	 * @param inProgressDrawable ��������ʾ��λͼ����Դ��ID
	 */
	public void setInProgressDrawable(int inProgressDrawable) {
		mInProgressDrawable = null;
		mInProgressDrawableResource = inProgressDrawable;
	}

	/**
	 * ���ö���
	 * @param animate AnimateAppearance
	 */
	public void setAnimateImageAppearance(AnimateAppearance animate) {
		switch (animate) {
		case ANIMATE_ALWAYS: {
			mAnimateImageAppearance = true;
			mAnimateImageAppearanceAfterDownload = true;
			break;
		}
		case ANIMATE_AFTER_DOWNLOAD: {
			mAnimateImageAppearance = false;
			mAnimateImageAppearanceAfterDownload = true;
			break;
		}
		case ANIMATE_NEVER: {
			mAnimateImageAppearance = false;
			mAnimateImageAppearanceAfterDownload = false;
			break;
		}
		default:
			break;
		}
	}

	/**
	 * ���������¼���ɼ�����
	 */
	public void setBitmapLoaderCallback(BitmapLoaderCallback callback) {
		mCallback = callback;
	}

	/**
	 * ����ͼƬ, ��������ÿ�θ��µ�ImageView
	 * @param url ͼƬ��ԴURL��ַ
	 * @param imageView ������ImageView
	 */
	public void download(String url, ImageView imageView) {
		//ʵ����һ��������
		Download d = new Download(url, imageView);
		//����ͼƬ
		d.loadImage();
	}

	/**
	 * ����ͼƬ���˷��������ھ���ˢ�µ�ͼƬ����, ������֤���
	 * @param url ͼƬ��ԴURL��ַ
	 * @param imageView ������ImageView
	 */
	public void reDownload(String url, ImageView imageView) {
		//ʵ����һ��������
		Download d = new Download(url, imageView);
		//��������ͼƬ
		d.reLoadImage();
	}

	/**
	 * ȡ�����е�����
	 */
	public void cancelAllDownloads() {
		mQueuedDownloads.clear();
		for (Download download : mRunningDownloads) {
			BitmapDownloaderTask task = download.getBitmapDownloaderTask();
			if (task != null) {
				task.cancel(true);
			}
		}
		mRunningDownloads.clear();
	}

	/**
	 * �������������ͼƬ������
	 * ͼƬ������������ʱ�����ȴ��ڴ滺���в��ң�������ڣ���ֱ��ʹ�ã�
	 * ��������ڣ�����SD����Ŀ¼�м��أ����SD���д��ڣ���ʹ�ã�
	 * ���SD����Ҳ�����ڣ���ֱ�Ӵ�����������
	 */
	public class Download implements BitmapDownloaderTask.BitmapDownloadListener, BitmapLoaderTask.BitmapLoadListener {
		/**
		 * λͼ��Դ������URL��ַ
		 */
		private String mUrl;
		/**
		 * �����ù�����ImageView����
		 */
		private WeakReference<ImageView> mImageViewRef;
		/**
		 * ����������ʵ��
		 */
		private BitmapDownloaderTask mBitmapDownloaderTask;
		/**
		 * SD��ͼƬ������ʵ��
		 */
		private BitmapLoaderTask mBitmapLoaderTask;
		/**
		 * �Ƿ���ȡ��
		 */
		private boolean mIsCancelled;
		/**
		 * �Ƿ��Ѿ����ع�
		 */
		private boolean mWasDownloaded = false;

		/**
		 * ͼƬ��������������
		 * @param url ͼƬ
		 * @param imageView
		 */
		public Download(String url, ImageView imageView) {
			//ƴװ��URL
			this.mUrl = ServerAddress.IMAGE_DOWNLOAD + url;
			//������
			this.mImageViewRef = new WeakReference<ImageView>(imageView);
			mIsCancelled = false;
			//ȡ��ԭ�ȵ�ͼƬ�����ó�Ĭ��ͼƬ
			imageView.setImageDrawable(null);
			imageView.setBackgroundResource(R.drawable.image_default);
		}

		/**
		 * ��ȡ����������ʵ��
		 * @return ����������ʵ��
		 */
		public BitmapDownloaderTask getBitmapDownloaderTask() {
			return mBitmapDownloaderTask;
		}

		/**
		 * ��ȡ��Ӧ�õ�imageView
		 * @return ��Ӧ�õ�imageView
		 */
		public ImageView getImageView() {
			return mImageViewRef.get();
		}

		/**
		 * ����ͼƬ��URL��Դ��ַ
		 * @return ͼƬ��URL��Դ��ַ
		 */
		public String getUrl() {
			return mUrl;
		}

		/**
		 * ����ͼƬ�����ʺϾ�����Ҫˢ�µ�ͼƬ
		 * ���ж��ڴ滺���Ƿ���ڣ������������ش�ͼƬ
		 * ����ڴ滺�治���ڣ����SD������ͼƬ
		 * ���SD���д�ͼƬ���ڣ�����ش�ͼƬ����������ӵ��ڴ滺����
		 * ���SD����Ҳû�����ͼƬ����ִ����������
		 */
		public void loadImage() {
			ImageView imageView = mImageViewRef.get();
			if (imageView != null) {
				//�õ������Bitmap
				Bitmap cachedBitmap = mBitmapCache.getBitmap(mUrl);
				// find the old download, cancel it and set this download as the current
				// download for the imageview
				//ͨ��tag�õ��ɵ�������ʵ��.��ȡ����
				Download oldDownload = (Download) imageView.getTag(DOWNLOAD_TAG);
				if (oldDownload != null) {
					oldDownload.cancel();
					Logg.d(TAG, "oldDownload.cancel()");
				}
				//�������ͼƬ����
				if (cachedBitmap != null) {
					Logg.d(TAG, "cachedBitmap != null");
					mWasDownloaded = false;
					BitmapDrawable bm = new BitmapDrawable(imageView.getResources(), cachedBitmap);
					loadDrawable(bm);
					imageView.setTag(DOWNLOAD_TAG, null);
				} else {
					//�ڴ��еĻ���ͼƬ������
					Logg.d(TAG, "cachedBitmap == null");
					imageView.setTag(DOWNLOAD_TAG, this);
					loadFromDisk(imageView);
				}
			}
		}
		
		/**
		 * ��������ͼƬ,��������Ҫÿ�θ��µ�ͼƬ
		 */
		public void reLoadImage() {
			ImageView imageView = mImageViewRef.get();
			if (imageView != null) {
				Logg.out("reLoadImage");
				imageView.setTag(DOWNLOAD_TAG, this);
				doDownload();
			}
		}

		/**
		 * ִ����������
		 */
		public void doDownload() {
			Logg.out("bbbbbbbbbbbbbbbbbbbbb");
			if (mIsCancelled) {
				// if the download has been cancelled, do not download
				// this image, but start the next one
				if (!mQueuedDownloads.isEmpty() && mRunningDownloads.size() < mMaxDownloads) {
					Download d = mQueuedDownloads.remove(0);
					d.doDownload();
				}
				return;
			}
			Logg.out("cccccccccccccccccccccccccccccccccc");
			ImageView imageView = mImageViewRef.get();
			if (imageView != null && imageView.getTag(DOWNLOAD_TAG) == this) {
				Logg.out("ddddddddddddddddddddddddddd");
				if(TextUtils.isEmpty(DEFAULT_PATH_PIC)) {
					//�������Ŀ¼Ϊ�գ���ͼƬ���浽/data/data/package/files����
					DEFAULT_PATH_PIC = imageView.getContext().getApplicationContext().getCacheDir().getAbsolutePath();
					mBitmapDownloaderTask = new BitmapDownloaderTask(imageView, this);
				} else {
					//��ͼƬ���ص�ָ���Ļ���Ŀ¼
					mBitmapDownloaderTask = new BitmapDownloaderTask(imageView, DEFAULT_PATH_PIC, this);
				}
				//���������е�ͼƬ
				loadInProgressDrawable(imageView);
				//ִ������
				mBitmapDownloaderTask.execute(mUrl);
				Logg.d(TAG, "doDownload: " + mUrl);
				//����ǰ���������뵽���������б���
				mRunningDownloads.add(this);
			}
		}

		/**
		 * �Ƿ�ʼ����
		 * @return ture����Ѿ������أ�false��δ��ʼ����
		 */
		private boolean isBeingDownloaded() {
			for (Download download : mRunningDownloads) {
				if (download == null) {
					continue;
				}
				ImageView otherImageView = download.getImageView();
				ImageView thisImageView = getImageView();
				if (thisImageView == null || otherImageView == null) {
					continue;
				}
				if (otherImageView.equals(thisImageView) && download.getUrl().equals(mUrl)) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * ��SD���м���ͼƬ,����ʾ��ImageView��
		 * @param imageView ͼƬ������ImageView
		 */
		private void loadFromDisk(ImageView imageView) {
			if (imageView != null && !mIsCancelled) {
				if(DEFAULT_PATH_PIC == null) {
					mBitmapLoaderTask = new BitmapLoaderTask(imageView, this);
				} else {
					mBitmapLoaderTask = new BitmapLoaderTask(imageView, DEFAULT_PATH_PIC, this);
				}
				try {
					mBitmapLoaderTask.execute(mUrl,"refresh");
				} catch (RejectedExecutionException e) {
				}
			}
		}

		/**
		 * ȡ������, �������غ�SD������ȫ��ȡ��
		 */
		private void cancel() {
			Logg.d(TAG, "cancel requested for: " + mUrl);
			mIsCancelled = true;
			//���б����Ƴ�
			if (mQueuedDownloads.contains(this)) {
				mQueuedDownloads.remove(this);
			}
			//ȡ������������
			if (mBitmapDownloaderTask != null) {
				mBitmapDownloaderTask.cancel(true);
			}
			//ȡ��SD��������
			if (mBitmapLoaderTask != null) {
				mBitmapLoaderTask.cancel(true);
			}
		}

		/**
		 * ����ͬһ��ImageView���������б������ز�ͬURL��Դ��ID
		 * @return ͬһ��ImageView���ز�ͬURL��Դ��ID, -1���û������������
		 */
		private int indexOfDownloadWithDifferentURL() {
			for (Download download : mRunningDownloads) {
				if (download == null) {
					continue;
				}
				ImageView otherImageView = download.getImageView();
				ImageView thisImageView = getImageView();
				if (thisImageView == null || otherImageView == null) {
					continue;
				}
				if (otherImageView.equals(thisImageView) && !download.getUrl().equals(mUrl)) {
					return mRunningDownloads.indexOf(download);
				}
			}
			return -1;
		}

		/**
		 * �鿴�������Ƿ����������б���
		 * @return true���������ͬ���أ�false�б��в�������ͬ����
		 */
		private boolean isQueuedForDownload() {
			for (Download download : mQueuedDownloads) {
				if (download == null) {
					continue;
				}
				ImageView otherImageView = download.getImageView();
				ImageView thisImageView = getImageView();
				if (thisImageView == null || otherImageView == null) {
					continue;
				}
				if (otherImageView.equals(thisImageView) && download.getUrl().equals(mUrl)) {
					return true;
				}
			}
			return false;
		}

		/**
		 * �鿴�������Ƿ����������б��У�������ڷ��������б��е�λ��
		 * @return �������ͬ�������б��е�λ�ã�-1���������������ͬ����
		 */
		private int indexOfQueuedDownloadWithDifferentURL() {
			for (Download download : mQueuedDownloads) {
				if (download == null) {
					continue;
				}
				ImageView otherImageView = download.getImageView();
				ImageView thisImageView = getImageView();
				if (thisImageView == null || otherImageView == null) {
					continue;
				}
				if (otherImageView.equals(thisImageView) && !download.getUrl().equals(mUrl)) {
					return mQueuedDownloads.indexOf(download);
				}
			}
			return -1;
		}

		/**
		 * �鿴������URL�Ƿ������б���
		 * @return true������б�������ش�URL��Դ��false�������������������ش�URL��Դ
		 */
		private boolean isAnotherQueuedOrRunningWithSameUrl() {
			for (Download download : mQueuedDownloads) {
				if (download == null) {
					continue;
				}
				if (download.getUrl().equals(mUrl)) {
					return true;
				}
			}
			for (Download download : mRunningDownloads) {
				if (download == null) {
					continue;
				}
				if (download.getUrl().equals(mUrl)) {
					return true;
				}
			}
			return false;
		}

		/**
		 * ����ͼƬ��ʾ��������ImageView��
		 * @param d ���õ�ImageView�ϵ�drawable
		 */
		private void loadDrawable(Drawable d) {
			loadDrawable(d, true);
		}

		/**
		 * ����ͼƬ��ʾ��������ImageView��
		 * @param d ���õ�ImageView�ϵ�drawable
		 * @param animate �Ƿ���ʾ����
		 */
		private void loadDrawable(Drawable d, boolean animate) {
			Logg.d(TAG, "loadDrawable: " + d);
			ImageView imageView = getImageView();
			if (imageView != null) {
				if (animate && (mAnimateImageAppearance || (mAnimateImageAppearanceAfterDownload && mWasDownloaded))) {
					Drawable current = imageView.getDrawable();
					if (current == null) {
						current = new ColorDrawable(Color.TRANSPARENT);
					}
					Drawable[] layers = { current, d };
					BitmapTransitionDrawable drawable = new BitmapTransitionDrawable(layers);
					drawable.setTransitionCallback(new BitmapTransitionCallback() {

						@Override
						public void onStarted() {
						}

						@Override
						public void onEnded() {
							ImageView imageView = getImageView();
							// the imageview tag must be null as we've already removed
							// ourselves as the tag from the imageview. If a new downloader is
							// using the imageview, ir would have set itself as the tag
							if (imageView != null && imageView.getTag() == null) {
								Drawable d = ((BitmapTransitionDrawable) imageView.getDrawable()).getDrawable(1);
								imageView.setImageDrawable(d);
								if (mCallback != null) {
									mCallback.onLoaded(imageView);
								}
							}
						}
					});
					imageView.setImageDrawable(drawable);
					 // fade out the old image
					drawable.setCrossFadeEnabled(true);
					drawable.startTransition(200);
				} else {
					imageView.setImageDrawable(d);
					if (mCallback != null) {
						mCallback.onLoaded(imageView);
					}
				}
			}
		}

		/**
		 * ������ɵ�ʱ�򱻻ص�
		 * ������ɺ󣬴�SD�����أ���������ӵ��ڴ滺����
		 * ��������л���δ���ص�URL��Դ����Ӷ����г���һ��ִ������
		 */
		@Override
		public void onComplete() {
			Logg.d(TAG, "onComplete: " + mUrl);
			//�������б����Ƴ�
			mRunningDownloads.remove(this);
			//����������
			mWasDownloaded = true;
			
			ImageView imageView = mImageViewRef.get();
			if (imageView != null && this == imageView.getTag(DOWNLOAD_TAG)) {
				//���imageview��δ�����գ���SD������ͼƬ
				loadFromDisk(getImageView());
			}

			//�鿴��URL����������
			ArrayList<Download> duplicates = mDuplicateDownloads.get(mUrl);
			if (duplicates != null) {
				for (Download dup : duplicates) {
					Logg.d(TAG, "onComplete: " + dup.mUrl);
					// load the image.
					//��ͬ���ذ���֪ͨȥ����ͼƬ
					if (dup.getImageView().getTag(DOWNLOAD_TAG) == dup) {
						dup.loadFromDisk(dup.getImageView());
					}
				}
				//�Ƴ���Щ��ͬ��URL����
				mDuplicateDownloads.remove(mUrl);
			}

			//��ȡ��һ�����أ���������
			if (!mQueuedDownloads.isEmpty()) {
				Download d = mQueuedDownloads.remove(0);
				d.doDownload();
			}
		}

		/**
		 * ���ش����ʱ��ص��˷���
		 */
		@Override
		public void onError() {
			Logg.d(TAG, "onError: " + mUrl);
			mRunningDownloads.remove(this);
			ImageView imageView = mImageViewRef.get();
			mWasDownloaded = true;
			if (imageView != null) {
				loadErrorDrawable(imageView);
			}

			if (imageView != null && this == imageView.getTag(DOWNLOAD_TAG)) {
				imageView.setTag(DOWNLOAD_TAG, null);
			}
			if (!mQueuedDownloads.isEmpty()) {
				Download d = mQueuedDownloads.remove(0);
				d.doDownload();
			}
		}

		/**
		 * �������ش���ʱͼƬ
		 * @param imageView ͼƬ������ImageView
		 */
		private void loadErrorDrawable(ImageView imageView) {
			if (mErrorDrawableResource == -1 && mErrorDrawable != null) {
				imageView.setImageDrawable(mErrorDrawable);
			} else if (mErrorDrawableResource != -1) {
				imageView.setImageResource(mErrorDrawableResource);
			} else {
				imageView.setImageResource(R.drawable.image_default);
			}
		}

		/**
		 * ��ʾ�����е�ͼƬ
		 * @param imageView ͼƬ������ImageView
		 */
		private void loadInProgressDrawable(ImageView imageView) {
			if (mInProgressDrawableResource == -1 && mInProgressDrawable != null) {
				imageView.setImageDrawable(mInProgressDrawable);
			} else if (mInProgressDrawableResource != -1) {
				imageView.setImageResource(mInProgressDrawableResource);
			} else {
				imageView.setImageResource(R.drawable.image_default);
			}
		}

		/**
		 * ����ȡ��ʱ���ص��˷���
		 * ȡ��ʱ�����б��г��ӣ�������һ��
		 */
		@Override
		public void onCancel() {
			mIsCancelled = true;
			Logg.d(TAG, "onCancel: " + mUrl);
			mRunningDownloads.remove(this);

			ImageView imageView = mImageViewRef.get();
			if (imageView != null && this == imageView.getTag(DOWNLOAD_TAG)) {
				imageView.setTag(DOWNLOAD_TAG, null);
			}
			if (!mQueuedDownloads.isEmpty()) {
				Download d = mQueuedDownloads.remove(0);
				Logg.d(TAG, "starting DL of: " + d.getUrl());
				d.doDownload();
			}
		}

		/**
		 * �ļ����ļ�ϵͳ��δ�ҵ�ʱ�ص��˷���
		 * ��������������ļ����ڣ������Ƿ������������ش�URL��
		 * ���û���������أ�����뵽���ض�����
		 */
		@Override
		public void notFound() {
			Logg.d(TAG, "notFound: " + mUrl);
			if (mIsCancelled){
				return;
			}
			ImageView imageView = getImageView();

			if (imageView == null || this != imageView.getTag(DOWNLOAD_TAG)) {
				return;
			}

			loadInProgressDrawable(imageView);

			if (isAnotherQueuedOrRunningWithSameUrl()) {
				if (mDuplicateDownloads.containsKey(mUrl)) {
					ArrayList<Download> arr = mDuplicateDownloads.get(mUrl);
					arr.add(this);
					mDuplicateDownloads.put(mUrl, arr);
				} else {
					ArrayList<Download> arr = new ArrayList<Download>();
					arr.add(this);
					mDuplicateDownloads.put(mUrl, arr);
				}
			} else {
				// check if this imageView is being used with a different URL, if so
				// cancel the other one.
				int queuedIndex = indexOfQueuedDownloadWithDifferentURL();
				int downloadIndex = indexOfDownloadWithDifferentURL();
				while (queuedIndex != -1) {
					mQueuedDownloads.remove(queuedIndex);
					Logg.d(TAG, "notFound(Removing): " + mUrl);
					queuedIndex = indexOfQueuedDownloadWithDifferentURL();
				}
				if (downloadIndex != -1) {
					//ȡ�����������м��ش�URLͼƬ�ļ�����
					Download runningDownload = mRunningDownloads.get(downloadIndex);
					BitmapDownloaderTask downloadTask = runningDownload.getBitmapDownloaderTask();
					if (downloadTask != null) {
						downloadTask.cancel(true);
						Logg.d(TAG, "notFound(Cancelling): " + mUrl);
					}
				}

				//��ӵ����ض����У�ִ������
				if (!(isBeingDownloaded() || isQueuedForDownload())) {
					if (mRunningDownloads.size() >= mMaxDownloads) {
						Logg.d(TAG, "notFound(Queuing): " + mUrl);
						mQueuedDownloads.add(this);
					} else {
						Logg.d(TAG, "notFound(Downloading): " + mUrl);
						doDownload();
					}
				}
			}
		}

		/**
		 * SD������ͼƬ��ɺ󣬻ص��˷�������ͼƬ��ʾ��ImageView�ϣ�
		 * ����ӵ��ڴ滺����
		 * @param b SD��������������ɷ��ص�λͼ
		 */
		@Override
		public void loadBitmap(Bitmap b) {
			Logg.d(TAG, "loadBitmap: " + mUrl);
			mBitmapCache.addBitmap(mUrl, b);
			ImageView imageView = getImageView();
			if (imageView != null && this == imageView.getTag(DOWNLOAD_TAG)) {
				BitmapDrawable bm = new BitmapDrawable(imageView.getResources(), b);
				loadDrawable(bm);
				imageView.setTag(DOWNLOAD_TAG, null);
			}
			mWasDownloaded = false;
		}

		/**
		 * ��SD�����ش���ʱ���ص��˷���
		 */
		@Override
		public void onLoadError() {
			Logg.d(TAG, "onLoadError: " + mUrl);
			ImageView imageView = getImageView();
			if (imageView != null && this == imageView.getTag(DOWNLOAD_TAG)) {
				imageView.setTag(DOWNLOAD_TAG, null);
				loadErrorDrawable(imageView);
			}
		}

		/**
		 * SD��������ȡ��ʱ���ص��˷���
		 * ����imageView��ʾ�����е�ͼƬ
		 */
		@Override
		public void onLoadCancelled() {
			Logg.d(TAG, "onLoadCancelled: " + mUrl);
			ImageView imageView = getImageView();
			if (imageView != null && this == imageView.getTag(DOWNLOAD_TAG)) {
				imageView.setTag(DOWNLOAD_TAG, null);
				loadInProgressDrawable(imageView);
			}
		}
	}

}
