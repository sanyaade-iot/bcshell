/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package org.bcsphere.bcshell;

import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewClient;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class BCShell extends CordovaActivity 
{
    private EditText web_url;
    private Button web_enter;
    private CordovaWebView web_content;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.init();
        setContentView(R.layout.bcshell);
        web_url = (EditText) findViewById(R.id.web_url);
        web_enter = (Button) findViewById(R.id.web_enter);
        web_content = (CordovaWebView) findViewById(R.id.web_content);
        web_content.setWebViewClient(new myWebViewClient(this,web_content));
        web_content.getSettings().setJavaScriptEnabled(true); 
        web_content.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY); 
        web_content.requestFocus(); 
        web_enter.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                String url_text; 
                String url_head = "http://"; 
                url_text=web_url.getText().toString();
                if("".equals(url_text.trim())){
                    return;
                }
                if(!url_text.startsWith("http://")){ 
                    url_text=url_head.concat(url_text); 
                } 
                web_content.loadUrl(url_text); 
                web_url.setText(url_text); 
            }
        });
        
    }
    
    private class myWebViewClient extends CordovaWebViewClient{

        public myWebViewClient(CordovaInterface cordova, CordovaWebView view) {
            super(cordova, view);
        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            web_url.setText(url);
        }
        
    } 
}

