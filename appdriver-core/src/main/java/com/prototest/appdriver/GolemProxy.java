package com.prototest.appdriver;


import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarNameValuePair;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.Proxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

public class GolemProxy {

    ProxyServer server;
    Proxy proxy;

    public GolemProxy(int port){
        server = new ProxyServer(port);
        try {
            server.start();
            proxy = server.seleniumProxy();
            server.newHar("");
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public Proxy getProxy(){
        return proxy;
    }

    public Har getHar(){
        return server.getHar();
    }

    public List<HarEntry> getEntries()
    {
        try
        {
            return getHar().getLog().getEntries();
        }
        catch (Exception e)
        {
            return new LinkedList<>();
        }
    }

    public List<HarEntry> filterEntries(String url)
    {
        try
        {

            List<HarEntry> filteredList = new LinkedList<HarEntry>();
            List<HarEntry> entries = getEntries();
            for(HarEntry entry : entries){
                if(entry.getRequest().getUrl().contains(url)){
                    filteredList.add(entry);
                    entry.getRequest().getQueryString();
                }
            }
            return filteredList;
        }
        catch (Exception e)
        {
            return new LinkedList<HarEntry>();
        }
    }

    public boolean isQueryStringInEntry(HarNameValuePair query,HarEntry entry)
    {
        if(entry.getRequest().getQueryString().contains(query)){
            return true;
        }
        return false;
    }

    public HarEntry getLastEntryForUrl(String url)
    {
        List<HarEntry> entries = filterEntries(url);
        return entries.get(entries.size()-1);
    }

    public String getValueForQueryStringWithName(String name, HarEntry entry)
    {

        List<HarNameValuePair> querieString = entry.getRequest().getQueryString();
        for(HarNameValuePair query : querieString){
            if(query.getName().contains(name)){
            return query.getValue();
            }
        }
        return null;
    }


    public void verifyQueryStringInEntry(HarNameValuePair queryString, HarEntry entry)
    {
        if (isQueryStringInEntry(queryString, entry))
        {
//            Common.Log(string.Format("!--Verification Passed. Request contains {0}={1}", queryString.Name,
//                    queryString.Value));
            return;
        }

        String message;
        String value = getValueForQueryStringWithName(queryString.getName(), entry);
        if (value != null)
        {
//            message = string.Format("Expected {0}={1}. Actual {2}={3}", queryString.Name, queryString.Value,
//                    queryString.Name, value);
        }
        else
        {
            message = String.format("No QueryString found with Description=%s", queryString.getName());
        }
//        TestBase.AddVerificationError(string.Format("Request From {0} to {1} not correct. {2}",
//                TestBase.testData.driver.Url, entry.Request.Url, message));
    }
//
    public void verifyRequestMade(String url)
    {
        List<HarEntry> entries = filterEntries(url);
        if (entries.size() == 0)
        {
            //TestBase.AddVerificationError("Did not find request with url " + url);
        }
    }

    public void verifyRequestQueryString(String url, HarNameValuePair queryString)
    {
        if (isQueryStringInEntry(queryString, getLastEntryForUrl(url)))
        {
//            Common.Log(string.Format("!--Verification Passed. Request contains {0}={1}", queryString.Name,
//                    queryString.Value));
            return;
        }

        String message;
        String value = getValueForQueryStringWithName(queryString.getName(), getLastEntryForUrl(url));
        if (value != null)
        {
//            message = string.Format("Expected {0}={1}. Actual {2}={3}", queryString.getName(), queryString.getValue(),
//                    queryString.Name, value);
        }
        else
        {
//            message = string.Format("No QueryString found with Description={0}", queryString.Name);
        }
//        TestBase.AddVerificationError(string.Format("Request From {0} to {1} not correct. {2}",
//                TestBase.testData.driver.Url, url, message));
    }


    public void saveHarToFile(String path) {
        try {
            PrintWriter out = new PrintWriter(path);
            getHar().writeTo(out);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


//    public String GetHarFilePath()
//    {
//        return Directory.GetCurrentDirectory()
//                + Path.DirectorySeparatorChar
//                + "HTTP_Traffic_" + Common.GetShortTestName(80) + ".har";
//    }
}
