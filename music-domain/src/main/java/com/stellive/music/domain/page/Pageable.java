package com.stellive.music.domain.page;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Pageable {
    private final int DEFAULT_PAGE_SIZE = 10; // 한 페이지에 담길 데이터 수
    private final int DEFAULT_BLOCK_SIZE = 5; // 블럭 개수 (blockSize: 3일 경우 예: [<][1][2][3][>])

    private int page;
    private int totalCount;
    private int totalPageCount;
    private int startPage;
    private int endPage;

    private int firstPage;
    private int prevPage;
    private int nextPage;
    private int lastPage;

    private int totalBlockCount;
    private int block;
    private int prevBlock;
    private boolean hasPrevBlock = false;
    private int nextBlock;
    private boolean hasNextBlock = false;

    private int startIndex;
    private int endIndex;

    @Builder
    public Pageable(int page, int totalCount) {
        settings(page, totalCount, DEFAULT_PAGE_SIZE, DEFAULT_BLOCK_SIZE);
    }

    @Builder
    public Pageable(int page, int totalCount, int pageSize, int blockSize) {
        if (pageSize <= 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        if (blockSize <= 0) {
            blockSize = DEFAULT_BLOCK_SIZE;
        }

        settings(page, totalCount, pageSize, blockSize);
    }

    private void settings(int page, int totalCount, int pageSize, int blockSize) {
        this.page = page <= 0 ? 1 : page;
        this.totalCount = totalCount;

        this.totalPageCount = (int) Math.ceil(totalCount * 1.0 / pageSize);
        this.totalBlockCount = (int) Math.ceil(totalPageCount * 1.0 / blockSize);

        this.block = (int) Math.ceil((this.page * 1.0) / blockSize);

        this.startPage = (block - 1) * blockSize + 1;
        this.endPage = (startPage - 1) + blockSize;

        if (endPage >= totalPageCount) {
            this.endPage = totalPageCount;
        }

        if (totalPageCount == 0) {
            this.endPage = 1;
        }

        this.firstPage = 1;
        this.lastPage = totalPageCount;

        this.prevPage = this.page - 1;
        if (prevPage < 1) this.prevPage = 1;
        this.nextPage = this.page + 1;
        if (totalPageCount < nextPage) nextPage = totalPageCount;

        this.prevBlock = (block - 2) * blockSize + 1;
        if (prevBlock < 1) this.prevBlock = 1;
        this.hasPrevBlock = block > 1;

        this.nextBlock = block * blockSize + 1;
        if (nextBlock > totalPageCount) nextBlock = totalPageCount;
        this.hasNextBlock = totalBlockCount > block;

        this.startIndex = (this.page - 1) * pageSize;
        this.endIndex = pageSize;
    }

    public boolean hasPrevBlock() {
        return this.hasPrevBlock;
    }

    public boolean hasNextBlock() {
        return this.hasNextBlock;
    }

    public int getOffset() {
        return this.startIndex;
    }

    public int getLimit() {
        return this.endIndex;
    }

}
