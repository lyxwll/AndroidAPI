package com.cloudy.androidapi.internet.bitmap;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
/**
 * λͼ��������
 *
 */
public class PhotoUtils {
	/**
	 * ��bytes����ת����bitmap��Ȼ�����maxSize����
	 * @param src Դbytes����
	 * @param maxSize ���ź��С
	 * @return �������ź��bitmap��bytes���飬������Դbytes���������OOM�������쳣
	 */
	public static byte[] scal(byte[] src, int maxSize) {
		try {
			Bitmap tempBitmap = byteToBitmap(src);
			Bitmap descBitmap = scal(tempBitmap, maxSize);
			byte[] rData = bitmapToByte(descBitmap);
			tempBitmap.recycle();
			descBitmap.recycle();
			return rData;
		} catch (OutOfMemoryError e) {
			return src;
		} catch (Exception e) {
			return src;
		}
	}

	/**
	 * ͼ������
	 * @param src Դλͼ
	 * @param maxSize ���ź�Ĵ�С
	 * @return ���ź��λͼ��null���ԴλͼΪ��
	 */
	public static Bitmap scal(Bitmap src, int maxSize) {
		if (src == null)
			return null;
		int width = src.getWidth();
		int height = src.getHeight();
		float scal = 1f;
		if (width > maxSize || height > maxSize) { //Դλͼ��size�������ź��size
			if (width > height) {
				scal = (float) maxSize / (float) width;
			} else {
				scal = (float) maxSize / (float) height;
			}
		}
		Matrix matrix = new Matrix();
		matrix.postScale(scal, scal);
		Bitmap rBitmap = Bitmap.createBitmap(src, 0, 0, src.getWidth(),
				src.getHeight(), matrix, true);
		return rBitmap;
	}

	/**
	 * ͼƬ����
	 * @param src ԭʼbitmap
	 * @param r ���ű�
	 * @return ���ź��ͼƬ���������OOM���򷵻�Դͼ
	 */
	public static Bitmap scal(Bitmap src, float r) {
		if (src == null)
			return null;
		Matrix matrix = new Matrix();
		matrix.postScale(r, r);
		try {
			Bitmap rBitmap = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
			return rBitmap;
		} catch (OutOfMemoryError e) {
			return src;
		}
	}

	/**
	 * ��bitmapת����bytes����, quality 50
	 * @param bitmap Դλͼ
	 * @return bitmap's bytes, null if bitmap is null
	 */
	public static byte[] bitmapToByte(Bitmap bitmap) {
		if (bitmap == null)
			return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
		return baos.toByteArray();
	}

	/**
	 * ��bitmapת����bytes����, quality 100
	 * @param bitmap Դλͼ
	 * @param fmt ѹ����ʽ
	 * @return bitmap's bytes, null if bitmap is null
	 */
	public static byte[] bitmapToByte(Bitmap bitmap, Bitmap.CompressFormat fmt) {
		if (bitmap == null)
			return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(fmt, 100, baos);
		return baos.toByteArray();
	}

	/**
	 * ��bytes����ת����bitmap
	 * @param bitmapData Դbytes����
	 * @return ת�����bitmap��null���bytesΪ�գ����߳�����OOM
	 */
	public static Bitmap byteToBitmap(byte[] bitmapData) {
		if (bitmapData == null)
			return null;
		Options options = new Options();
		options.inJustDecodeBounds = true;
		int srcWidth = options.outWidth;
		options.inJustDecodeBounds = false;
		int be = 0;
		be = (int) Math.round(((double) srcWidth) / ((double) 80));
		options = new Options();
		options.inSampleSize = be;
		try {
			return BitmapFactory.decodeByteArray(bitmapData, 0,
					bitmapData.length, options);
		} catch (OutOfMemoryError e) {
			return null;
		}
	}

	/**
	 * ��bytes����ת����λͼ�������ų�size��С
	 * @param bitmapData Դbytes����
	 * @param size ���ź�Ĵ�С
	 * @return ת�������ź��λͼ��null���bytesΪ�գ����߳�����OOM
	 */
	public static Bitmap byteToBitmap(byte[] bitmapData, int size) {
		if (bitmapData == null)
			return null;
		Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory
				.decodeByteArray(bitmapData, 0, bitmapData.length, options);
		int w = options.outWidth;
		int h = options.outHeight;
		int sc = 0;
		if (w > size || h > size)
			if (w > h) {
				sc = Math.round((float) w / (float) size);
			} else {
				sc = Math.round((float) h / (float) size);
			}
		options.inJustDecodeBounds = false;
		options.inSampleSize = sc;
		try {
			return BitmapFactory.decodeByteArray(bitmapData, 0,
					bitmapData.length, options);
		} catch (OutOfMemoryError e) {
			return null;
		}
	}

	/**
	 * λͼ��ת
	 * @param b Դλͼ
	 * @param degrees ��ת�Ƕ�
	 * @return ��ת���λͼ��null ���ԭλͼbΪ�ջ���degreesΪ��
	 * 		   �����ת���ͼƬ�Ժ�Դλͼһ�£������OOM���򷵻�Դλͼ��
	 */
	public static Bitmap rotate(Bitmap b, int degrees) {
		if (degrees != 0 && b != null) {
			Matrix m = new Matrix();
			m.setRotate(degrees, (float) b.getWidth() / 2,
					(float) b.getHeight() / 2);
			try {
				Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(),
						b.getHeight(), m, true);
				if (b != b2) {
					return b2;
				} else {
					return b;
				}
			} catch (OutOfMemoryError ex) {
				return b;
			}
		}
		return null;
	}

	/**
	 * ����λͼ��ת���λ��
	 * @param bm Դλͼ
	 * @param orientationDegree ˮƽ�������ת�Ƕ�
	 * @return ������λͼ
	 */
	public Bitmap adjustPhotoRotation(Bitmap bm, final int orientationDegree) {
		Matrix m = new Matrix();
		m.setRotate(orientationDegree, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
		float targetX, targetY;
		if (orientationDegree == 90) {
			targetX = bm.getHeight();
			targetY = 0;
		} else {
			targetX = bm.getHeight();
			targetY = bm.getWidth();
		}
		final float[] values = new float[9];
		m.getValues(values);
		float x1 = values[Matrix.MTRANS_X];
		float y1 = values[Matrix.MTRANS_Y];
		m.postTranslate(targetX - x1, targetY - y1);
		Bitmap bm1 = Bitmap.createBitmap(bm.getHeight(), bm.getWidth(), Bitmap.Config.ARGB_8888);
		Paint paint = new Paint();
		Canvas canvas = new Canvas(bm1);
		canvas.drawBitmap(bm, m, paint);
		return bm1;
	}

	/**
	 * ��path·��loadingλͼ�������ŵ�size��С
	 * @param path Դλͼ�洢·��
	 * @param size ���ź�Ĵ�С
	 * @return loading���λͼ��null�������OOM
	 */
	public static Bitmap loadFile(String path, int size) {
		Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		int w = options.outWidth;
		int h = options.outHeight;
		int sc = 1;
		if (w > size || h > size)
			if (w > h) {
				sc = Math.round((float) w / (float) size);
			} else {
				sc = Math.round((float) h / (float) size);
			}
		options.inJustDecodeBounds = false;
		options.inSampleSize = sc;
		try {
			return BitmapFactory.decodeFile(path, options);
		} catch (OutOfMemoryError e) {
			return null;
		}
	}

	/**
	 * ��path·��loadingλͼ
	 * @param path Դλͼ�Ĵ洢·��
	 * @return loading���λͼ��null���������OOM
	 */
	public static Bitmap loadFile(String path) {
		try {
			return BitmapFactory.decodeFile(path);
		} catch (OutOfMemoryError e) {
			return null;
		}
	}

	/**
	 * ��bitmapת����Բ��λͼ
	 * @param bitmap Դλͼ
	 * @param pixels Բ�ǰ뾶
	 * @return ת�����λͼ
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), bitmap.getConfig());
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * ��filePath·���ж�ȡλͼ������λͼת����Բ��λͼ
	 * @param filePath Դλͼ�Ĵ洢·��
	 * @param pixels Բ�ǵİ뾶
	 * @return ���ش�����λͼ��null��ȡλͼʧ�ܻ��߳�����OOM
	 */
	public static Bitmap roundCornerFromFile(String filePath, int pixels) {
		try {
			Bitmap bitmap = BitmapFactory.decodeFile(filePath);
			if (bitmap == null)
				return null;
			Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
					bitmap.getHeight(), bitmap.getConfig());
			Canvas canvas = new Canvas(output);
			final int color = 0xff424242;
			final Paint paint = new Paint();
			final Rect rect = new Rect(0, 0, bitmap.getWidth(),
					bitmap.getHeight());
			final RectF rectF = new RectF(rect);
			final float roundPx = pixels;
			paint.setAntiAlias(true);
			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(color);
			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
			paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
			canvas.drawBitmap(bitmap, rect, rect, paint);
			bitmap.recycle();
			bitmap = null;
			return output;
		} catch (OutOfMemoryError e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * ��filePath·���ж�ȡλͼ���������ŵ�size��С����ת����Բ��
	 * @param filePath Դλͼ�Ĵ洢·�� 
	 * @param pixels Բ�ǵİ뾶
	 * @param size ���ź�Ĵ�С
	 * @return ���ش�����λͼ��null��ȡԴλͼ������߳�����OOM�쳣
	 */
	public static Bitmap roundCornerFromFile(String filePath, int pixels, int size) {
		try {
			Bitmap bitmap = loadFile(filePath, size);
			if (bitmap == null)
				return null;
			Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
					bitmap.getHeight(), bitmap.getConfig());
			Canvas canvas = new Canvas(output);
			final int color = 0xff424242;
			final Paint paint = new Paint();
			final Rect rect = new Rect(0, 0, bitmap.getWidth(),
					bitmap.getHeight());
			final RectF rectF = new RectF(rect);
			final float roundPx = pixels;
			paint.setAntiAlias(true);
			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(color);
			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
			paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
			canvas.drawBitmap(bitmap, rect, rect, paint);
			bitmap.recycle();
			bitmap = null;
			return output;
		} catch (OutOfMemoryError e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
