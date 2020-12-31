package com.jun.reservation.response;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jun  自定义分页类
 * @date 2020/12/31
 */
public class Pagination<T> {

    private List<T> dataList = new ArrayList<>();  // 当前页所有记录

    private Integer totalPages;  // 总页数

    private Long totalCounts;  // 记录总数

    private Integer currentPage; // 当前页数

    public Pagination() {
    }

    /**
     *
     * @param dataList
     * @param totalPages
     * @param totalCounts
     * @param currentPage
     */
    public Pagination(List<T> dataList, Integer totalPages, Long totalCounts, Integer currentPage) {
        this.dataList = dataList;
        this.totalPages = totalPages;
        this.totalCounts = totalCounts;
        this.currentPage = currentPage;
    }

    /***
     * 将JPA Page类 转化成自定义的分页类
     * @param result
     * @param <T>
     * @return
     */
    public static <T> Pagination<T> convert(Page<T> result) {
        Pagination<T> pagination = new Pagination<>();
        pagination.setDataList(result.getContent());
        pagination.setTotalPages(result.getTotalPages());
        pagination.setTotalCounts(result.getTotalElements());
        pagination.setCurrentPage(result.getNumber() + 1);
        return pagination;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Long totalCounts) {
        this.totalCounts = totalCounts;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
