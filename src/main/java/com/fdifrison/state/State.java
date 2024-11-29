package com.fdifrison.state;


public sealed interface State permits InProgress, InReview, Approved, Rejected {
}

