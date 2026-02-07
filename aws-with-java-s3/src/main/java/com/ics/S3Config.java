package com.ics;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
@Configuration
public class S3Config {


   @Value("${cloud.aws.credentials.secret-key}")
   private String secretKey;
   @Value("${cloud.aws.credentials.access-key}")
   private String accessKey;
   @Value("${cloud.aws.region.static}")
   private String region;


   @Bean
   public S3Client getS3Client(){
       AwsBasicCredentials basicCreds=AwsBasicCredentials.create(accessKey,secretKey);
       return  S3Client.builder()
               .region(Region.of(region)) // need to provide region of our S3 bucket
               .credentialsProvider(StaticCredentialsProvider.create(basicCreds)) // // need to provide creds of our S3 bucket
               .build();


   }


}
