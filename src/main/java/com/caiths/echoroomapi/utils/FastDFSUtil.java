package com.caiths.echoroomapi.utils;

import org.csource.common.MyException;  // FastDFS 自定义异常类
import org.csource.fastdfs.ClientGlobal;  // FastDFS 全局客户端配置类
import org.csource.fastdfs.StorageClient1;  // FastDFS 存储客户端类
import org.csource.fastdfs.TrackerClient;  // FastDFS 追踪客户端类
import org.csource.fastdfs.TrackerServer;  // FastDFS 追踪服务器类
import org.springframework.web.multipart.MultipartFile;  // Spring 的文件上传接口

import java.io.IOException;  // 输入输出异常类

/**
 * FastDFS 文件操作工具类 (FastDFSUtil)。
 * <p>
 * 该类封装了 FastDFS 文件上传功能，提供文件上传到分布式文件系统的方法。
 * </p>
 *
 * @author poboll
 * @since 2025-02-26
 */
public class FastDFSUtil {
  /**
   * FastDFS 存储客户端实例，用于文件上传操作。
   */
  private static StorageClient1 client1;

  /**
   * 静态初始化块，用于初始化 FastDFS 客户端。
   * 通过加载配置文件创建 StorageClient1 实例。
   */
  static {
    try {
      // 初始化 FastDFS 客户端全局配置
      ClientGlobal.initByProperties("fastdfs-client.properties");
      // 创建追踪客户端
      TrackerClient trackerClient = new TrackerClient();
      // 获取追踪服务器连接
      TrackerServer trackerServer = trackerClient.getConnection();
      // 初始化存储客户端
      client1 = new StorageClient1(trackerServer, null);
    } catch (IOException e) {
      // 处理输入输出异常
      e.printStackTrace();
    } catch (MyException e) {
      // 处理 FastDFS 自定义异常
      e.printStackTrace();
    }
  }

  /**
   * 上传文件到 FastDFS。
   * 将 MultipartFile 文件上传到 FastDFS，并返回上传后的文件路径。
   *
   * @param file 要上传的文件，类型为 MultipartFile
   * @return 上传后文件的访问路径
   * @throws IOException 如果文件读取或网络操作失败
   * @throws MyException 如果 FastDFS 操作发生异常
   */
  public static String upload(MultipartFile file) throws IOException, MyException {
    // 获取原始文件名
    String oldName = file.getOriginalFilename();
    // 提取文件扩展名（从最后一个点开始）oldName.substring(oldName.lastIndexOf(".")+1)
    String ext = oldName.substring(oldName.lastIndexOf(".") + 1);
    // 上传文件并返回文件路径
    return client1.upload_file1(file.getBytes(), ext, null);
  }

//  public static StringBuilder getToken(String fileId) throws UnsupportedEncodingException, NoSuchAlgorithmException, MyException {
//    int ts = (int) Instant.now().getEpochSecond();
//    fileId=fileId.substring(7);
//    String token = ProtoCommon.getToken(fileId, ts, "FastDFS1234567890");
//    StringBuilder sb = new StringBuilder();
//    sb.append("?token=").append(token);
//    sb.append("&ts=").append(ts);
//    return sb;
//  }
  /**
   * 返回对象的字符串表示形式，便于调试和日志记录。
   *
   * @return 对象的字符串表示
   */
  @Override
  public String toString() {
    return "FastDFSUtil{" +
            "client1=" + client1 +
            '}';
  }
}
