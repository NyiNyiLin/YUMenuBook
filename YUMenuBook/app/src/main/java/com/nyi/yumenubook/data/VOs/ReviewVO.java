package com.nyi.yumenubook.data.VOs;

/**
 * Created by IN-3442 on 03-Dec-16.
 */

public class ReviewVO {
    private String reviewName;
    private String reviewDate;
    private String reviewReview;

    private String reviewPhotoURL;

    public ReviewVO() {
    }

    public ReviewVO(String reviewName, String reviewDate, String reviewReview, String reviewPhotoURL) {
        this.reviewName = reviewName;
        this.reviewDate = reviewDate;
        this.reviewReview = reviewReview;
        this.reviewPhotoURL = reviewPhotoURL;
    }

    public String getReviewName() {
        return reviewName;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public String getReviewReview() {
        return reviewReview;
    }

    public String getReviewPhotoURL() {
        return reviewPhotoURL;
    }
}
