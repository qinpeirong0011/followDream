package com.qinpr.follow.messageQueue.store;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by qinpr on 2019/2/19.
 */
public class MappedFileQueue {

    //存储路劲
    private final String storePath;
    //单个文件的存储大小
    private final int mappedFileSize;
    //创建MappedFile服务类
    private final AllocateMappedFileService allocateMappedFileService;

    private final CopyOnWriteArrayList<MappedFile> mappedFiles = new CopyOnWriteArrayList<MappedFile>();

    public MappedFileQueue(final String storePath, int mappedFileSize,
                           AllocateMappedFileService allocateMappedFileService) {
        this.storePath = storePath;
        this.mappedFileSize = mappedFileSize;
        this.allocateMappedFileService = allocateMappedFileService;
    }

    public MappedFile getLastMappedFile() {
        MappedFile mappedFileLast = null;
        while (!this.mappedFiles.isEmpty()) {
            try {
                mappedFileLast = this.mappedFiles.get(this.mappedFiles.size() - 1);
                break;
            } catch (IndexOutOfBoundsException e) {

            } catch (Exception e) {
                break;
            }
        }
        return mappedFileLast;
    }
}
