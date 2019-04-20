package com.blog.tenyears.utils.pagingPlugin.context;

/**
 * 分页参数
 * 
 * @author 23853
 *
 */
public class SystemContext {

	public static final Integer DEFAULT_PAGE_NUM = 1;
	public static final Integer DEFAULT_PAGE_SIZE = 2;

	private static ThreadLocal<Integer> pageNumHolder = new ThreadLocal<>();
	private static ThreadLocal<Integer> pageSizeHolder = new ThreadLocal<>();

	public static void setPageNum(Integer pageNum) {
		if (pageNum == null || pageNum <= 0) {
			pageNumHolder.set(DEFAULT_PAGE_NUM);
		} else {
			pageNumHolder.set(pageNum);
		}
	}

	public static void setPageSize(Integer pageSize) {
		if (pageSize == null || pageSize <= 0) {
			pageSizeHolder.set(DEFAULT_PAGE_SIZE);
		} else {
			pageSizeHolder.set(pageSize);
		}
	}

	public static Integer getPageNum() {
		Integer pageNum = pageNumHolder.get();
		if (pageNum == null) {
			return DEFAULT_PAGE_NUM;
		}

		return pageNum;
	}

	public static Integer getPageSize() {
		Integer pageSize = pageSizeHolder.get();
		if (pageSize == null) {
			return DEFAULT_PAGE_SIZE;
		}

		return pageSize;
	}

	public static void removePageNumHolder() {
		pageNumHolder.remove();
	}

	public static void removePageSizeHolder() {
		pageSizeHolder.remove();
	}
}
