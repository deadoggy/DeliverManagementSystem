package com.deliver.service;

import com.deliver.dao.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpDirEntry;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Iterator;

/**
 * Created by deadoggy on 17-6-1.
 */

public class FTPService {

    @Autowired
    private PackageRepository packageRepository;

    private static FtpClient client;
    private static String user = "ftpuser";
    private static String passwd = "123456";
    private static String ip = "139.129.18.35";
    private static int port = 21;
    private static Object lock = new Object();

    private static FTPService instance = null;

    private FTPService(){
        client = FtpClient.create();
        SocketAddress socket = new InetSocketAddress(ip, port);
        try{
            client.connect(socket);
        }catch(Exception e){
        }
    }

    public static FTPService getInstantce(){
        if(null != instance){
            return instance;
        }

        synchronized (lock){
            instance = new FTPService();
        }
        return instance;
    }

    public void upload(InputStream fileIn, String toFileName) throws Exception{
        try{

            client.connect(new InetSocketAddress(ip,port));
            client.login(user, passwd.toCharArray());


            BufferedInputStream in = new BufferedInputStream(fileIn);

            client.putFile(toFileName, in);

            in.close();

            client.close();

        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void upload(String fromPath, String toFileName) throws Exception {
        try{
            client.connect(new InetSocketAddress(ip,port));

            client.login(user, passwd.toCharArray());

            File upFile = new File(fromPath);

            if(!upFile.exists()){ //文件不存在
                throw new Exception("no such file");
            }

            BufferedInputStream in = new BufferedInputStream(new FileInputStream(upFile));

            client.putFile(toFileName, in);

            in.close();
            client.close();

            upFile.delete();

        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public String download(String remoteFileName, String localFileName) throws Exception {
        try{

            if(remoteFileName == null){
                throw new Exception("invalid file name");
            }

            if(null == localFileName){
                localFileName = remoteFileName;
            }

            client.login(user, passwd.toCharArray());

            File downFile = new File("File/"+localFileName);

            BufferedOutputStream of = new BufferedOutputStream(new FileOutputStream(downFile));

            client.getFile(remoteFileName, of);

            of.flush();
            of.close();

            return "File/" + localFileName;

        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    public boolean find(String fileName){
        try{
            client.login(user, passwd.toCharArray());
            Iterator<FtpDirEntry> itr = client.listFiles(fileName);
            return itr.hasNext();
        }catch(Exception e){
            return false;
        }
    }

}
