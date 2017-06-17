package com.deliver.service;

import com.deliver.dao.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpDirEntry;

import javax.servlet.http.HttpServletResponse;
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
    SocketAddress socket = new InetSocketAddress(ip, port);

    private FTPService(){
        try{
            client = FtpClient.create();
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


            client.connect(socket);
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
            client.connect(socket);

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
            if(!client.isConnected()){
                client.connect(socket);
            }


            client.login(user, passwd.toCharArray());

            String url = this.getClass().getResource("/").toURI().toString() + "../../static/" + localFileName;

            url = url.substring(5);

            File downFile = new File(url);

            if(!downFile.exists()){
                downFile.createNewFile();
            }

            BufferedOutputStream of = new BufferedOutputStream(new FileOutputStream(downFile));

            client.getFile(remoteFileName, of);


            of.flush();
            of.close();
            client.close();
            return url;

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

    public boolean getHeadImage(String fileName){
        try{
            if(null == fileName){
                throw new Exception("null file name");
            }

            this.download(fileName, fileName);

            return true;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
