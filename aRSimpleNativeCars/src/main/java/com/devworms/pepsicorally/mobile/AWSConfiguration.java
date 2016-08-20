//
// Copyright 2016 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.8
//
package com.devworms.pepsicorally.mobile;

import com.amazonaws.regions.Regions;

/**
 * This class defines constants for the developer's resource
 * identifiers and API keys. This configuration should not
 * be shared or posted to any public source code repository.
 */
public class AWSConfiguration {

    // AWS MobileHub user agent string
    public static final String AWS_MOBILEHUB_USER_AGENT =
        "MobileHub 1947ac17-0373-4d37-95b4-6ebc2509f1f3 aws-my-sample-app-android-v0.8";
    // AMAZON COGNITO
    public static final Regions AMAZON_COGNITO_REGION =
      Regions.fromName("us-east-1");
    public static final String  AMAZON_COGNITO_IDENTITY_POOL_ID =
        "us-east-1:61ce65a4-bcdd-490c-bcba-54620506fec8";
    // GOOGLE CLOUD MESSAGING API KEY
    public static final String GOOGLE_CLOUD_MESSAGING_API_KEY =
        "AIzaSyBSzrgAjs_P3dLYtxo0P0apcJ3rk8Jm2wE";
    // GOOGLE CLOUD MESSAGING SENDER ID
    public static final String GOOGLE_CLOUD_MESSAGING_SENDER_ID =
        "655591449892";

    // SNS PLATFORM APPLICATION ARN
    public static final String AMAZON_SNS_PLATFORM_APPLICATION_ARN =
        "arn:aws:sns:us-east-1:534003673918:app/GCM/artoolkit_MOBILEHUB_754666414";
    public static final Regions AMAZON_SNS_REGION =
         Regions.fromName("us-east-1");
    // SNS DEFAULT TOPIC ARN
    public static final String AMAZON_SNS_DEFAULT_TOPIC_ARN =
        "arn:aws:sns:us-east-1:534003673918:artoolkit_alldevices_MOBILEHUB_754666414";
    // SNS PLATFORM TOPIC ARNS
    public static final String[] AMAZON_SNS_TOPIC_ARNS =
        {};
}
