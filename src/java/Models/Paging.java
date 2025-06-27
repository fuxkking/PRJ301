package Models;

import java.util.List;

public class Paging {
    private List<Product> currentPageItems;
    private int currentPage;
    private int totalPages;

    public Paging() {}

    public Paging(List<Product> currentPageItems, int currentPage, int totalPages) {
        this.currentPageItems = currentPageItems;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public List<Product> getCurrentPageItems() {
        return currentPageItems;
    }

    public void setCurrentPageItems(List<Product> currentPageItems) {
        this.currentPageItems = currentPageItems;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "Paging{" + "currentPageItems=" + currentPageItems + ", currentPage=" + currentPage + ", totalPages=" + totalPages + '}';
    }
    
}
