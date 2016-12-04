package com.nyi.yumenubook.data.VOs;

/**
 * Created by IN-3442 on 03-Dec-16.
 */

public class ReviewVO {
    private String reviewName;
    private String reviewEmail;
    private String reviewDate;
    private String reviewReview;

    private String reviewPhotoURL;

    public ReviewVO() {
    }

    public ReviewVO(String reviewName, String reviewDate, String reviewEmail, String reviewReview, String reviewPhotoURL) {
        this.reviewName = reviewName;
        this.reviewEmail = reviewEmail;
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

    public String getReviewEmail() {
        return reviewEmail;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public void setReviewReview(String reviewReview) {
        this.reviewReview = reviewReview;
    }

    public void setReviewPhotoURL(String reviewPhotoURL) {
        this.reviewPhotoURL = reviewPhotoURL;
    }

    public void setReviewEmail(String reviewEmail) {
        this.reviewEmail = reviewEmail;
    }
}
