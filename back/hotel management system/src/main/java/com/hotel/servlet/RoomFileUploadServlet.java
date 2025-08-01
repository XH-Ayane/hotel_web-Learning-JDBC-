package com.hotel.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/roomFileUploadServlet")
public class RoomFileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 上传文件存储目录 - 将在init方法中初始化
    private String UPLOAD_DIRECTORY;

    @Override
    public void init() throws ServletException {
        // 获取webapp目录的绝对路径
        String webappPath = getServletContext().getRealPath("/");
        UPLOAD_DIRECTORY = webappPath + "uploads";

        System.out.println("文件上传目录: " + UPLOAD_DIRECTORY);
        // 确保上传目录存在
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 50; // 50MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 100; // 100MB

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置响应类型
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        // 检查是否为multipart请求
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则返回错误信息
            response.getWriter().write("{\"code\": 400, \"message\": \"不是multipart请求\", \"data\": null}");
            return;
        }

        // 创建文件上传配置
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 使用初始化的上传目录
        String uploadPath = this.UPLOAD_DIRECTORY;

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // 迭代处理表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        // 获取房间ID参数
                        String roomId = null;
                        for (FileItem formItem : formItems) {
                            if (formItem.isFormField() && "roomId".equals(formItem.getFieldName())) {
                                roomId = formItem.getString("UTF-8");
                                break;
                            }
                        }

                        if (roomId == null || roomId.isEmpty()) {
                            response.getWriter().write("{\"code\": 400, \"message\": \"房间ID不能为空\", \"data\": null}");
                            return;
                        }

                        // 创建房间特定的目录
                        String roomDirPath = uploadPath + File.separator + roomId;
                        File roomDir = new File(roomDirPath);
                        if (!roomDir.exists()) {
                            roomDir.mkdirs();
                        }

                        String filePath = roomDirPath + File.separator + fileName;
                        File storeFile = new File(filePath);

                        // 保存文件到硬盘
                        item.write(storeFile);

                        // 构建文件URL - 使用相对路径，确保以/开头
                        String fileUrl = "/uploads" + File.separator + roomId + File.separator + fileName;
                        // 替换反斜杠为正斜杠
                        fileUrl = fileUrl.replace(File.separator, "/");

                        // 返回成功信息，使用Element Plus上传组件期望的格式
                        Map<String, Object> result = new HashMap<>();
                        result.put("success", true);
                        result.put("message", "上传成功");
                        result.put("url", fileUrl);

                        ObjectMapper mapper = new ObjectMapper();
                        response.getWriter().write(mapper.writeValueAsString(result));
                        return;
                    }
                }
            }
        } catch (Exception ex) {
            response.getWriter()
                    .write("{\"code\": 500, \"message\": \"上传失败：" + ex.getMessage() + "\", \"data\": null}");
            ex.printStackTrace();
        }

        // 如果以上代码没有返回，说明上传失败
        response.getWriter().write("{\"code\": 400, \"message\": \"上传失败，没有找到文件\", \"data\": null}");
    }
}