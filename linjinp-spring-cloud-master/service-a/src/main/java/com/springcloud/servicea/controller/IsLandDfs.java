package com.springcloud.servicea.controller;

/**
 * DFS 深度优先算法实现岛屿个数探索
 * @author: linjinp
 * @create: 2019-07-30 15:59
 **/
public class IsLandDfs {

    /**
     * 判断是否是岛屿，然后统计岛屿数量
     * @param isLand 未知海域数组
     * @return 返回岛屿数量
     */
    public static Integer isLand(int[][] isLand) {

        // 都没东西玩啥
        if (isLand.length == 0) {
            return 0;
        }

        // 岛屿数量
        int count = 0;

        // 记录已经探索过的岛屿，其中里面的值，0 表示未探索，1 表示已探索
        int[][] isVisit = new int[isLand.length][isLand[0].length];

        // 循环未知海域的二维数组
        for (int i = 0; i < isLand.length; i ++) {
            for (int j = 0; j < isLand[i].length; j ++) {

                // isVisit 为 0 表示海域未探索岛屿
                // 这个判断的目的在于
                // 如果这个区域已经探索过了，这个岛屿与之前的岛屿相连，不增加岛屿数量
                // 如果这个是个新岛屿，那么岛屿周围肯定都是海水，岛屿探索是无法探索到这的
                // 不理解的可以把整个逻辑读懂后，思考下
                if (isVisit[i][j] == 0) {

                    // isLand 为 1 表示这个是岛屿（根节点），你也可以看作最初的登陆点
                    if (isLand[i][j] == 1) {
                        count ++;
                        System.out.println("岛屿登陆点（根节点），这是发现的第" + count + "座岛屿");
                    }
                }
                // 开始 DFS 深度优先算法探索岛屿
                visitLand(isLand, isVisit, i, j);
            }
        }
        return count;
    }

    /**
     *  DFS 深度优先算法岛屿探索，从岛屿的根节点开始
     * 把与这个岛屿相连的岛屿全部在 isVisit 中做上标记
     * @param isLand
     * @param isVisit
     * @param i
     * @param j
     */
    public static void visitLand(int[][] isLand, int[][] isVisit, int i, int j) {

        // 判断越界
        // 为何哟判断越界，有些人可能不太明白，i 和 j 都是从 0 开始的，怎么可能小于 0 呢？
        // 因为下面需要对所有相邻的区域去探索就会出现越界的情况
        // 比如 i = 0, j = 0 点为岛屿，我要向左探索，这时候 i - 1 = -1，这时就会出现越界的情况
        // 这让我不禁想起玩 RPG 游戏的时候，有时候走到地图外边就报错了，估计就是跨域没判断好
        if (i < 0 || j < 0 || i >= isLand.length || j >= isLand[0].length) {
            return;
        }

        // 遇到海洋了，不是岛屿不探索
        if (isLand[i][j] == 0) {
            return;
        }

        // 已经探索过的与岛屿相连的地方
        // 什么意思？就是你是从那里探索过来的，不需要再探索回去了
        if (isVisit[i][j] == 1) {
            return;
        }

        // 记录下这个点探索过了
        isVisit[i][j] = 1;

        // 开始探索陆地
        // 这里注意：i 是循环行， j 是循环列
        // 比如： i = 2，i - 1 就是从第 2 行向第 1 行去探索，是向上
        // 不理解的可以好好捋一捋
        // 向岛屿上面探索
        visitLand(isLand, isVisit, i - 1, j);
        // 向岛屿下面探索
        visitLand(isLand, isVisit, i + 1, j);
        // 向岛屿左边探索
        visitLand(isLand, isVisit, i, j - 1);
        // 向岛屿右边探索
        visitLand(isLand, isVisit, i, j + 1);
    }

    public static void main(String[] args) {

        // 岛屿 二维数组（图形化）
        int [][] isLand = {{1, 1, 0, 0, 0},{1, 1, 0, 0, 0},{0, 0, 1, 0, 0},{0, 0, 0, 1, 1}};
        int count = isLand(isLand);
        System.out.println("一共探索到" + count + "座岛屿");
    }
}
