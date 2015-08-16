package com.cloudy.androidapi.internet.bitmap;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
/**
 * 图片缓存
 *
 */
public class BitmapCache {
	private LruCache<String, Bitmap> mBitmapCache;

	public BitmapCache() {
		// 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常。 
	    // LruCache通过构造函数传入缓存值，以B为单位。 
		int maxMemory = (int) (Runtime.getRuntime().maxMemory()); 
		// 使用最大可用内存值的1/8作为缓存的大小。 
	    int cacheSize = maxMemory / 8; 
		mBitmapCache = new LruCache<String, Bitmap>(cacheSize) { // by default use 3mb as a limit for the in memory Lrucache
			//重写此方法来衡量每张图片的大小，默认返回图片数量。 
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				//The cache size will be measured in bytes rather than number of items.
				int byteCount = 0;
				//if (Build.VERSION.SDK_INT < 12) {
					byteCount = bitmap.getRowBytes() * bitmap.getHeight();
				//} else {
				//	byteCount = bitmap.getByteCount();
				//}
				return byteCount;
			}
		};
	}

	/**
	 * 想缓存中添加一张图片
	 * @param url 图片路径，作为key值
	 * @param b 原位图
	 */
	public void addBitmap(String url, Bitmap b) {
		mBitmapCache.put(url, b);
	}

	/**
	 * 从缓存中获取图片
	 * @param url 图片的路径
	 * @return 缓存的图片， null如果路径为空，或者缓存图片不存在
	 */
	public Bitmap getBitmap(String url) {
		if (url == null) {
			return null;
		}
		return mBitmapCache.get(url);
	}
}
