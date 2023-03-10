//给你一个在 XY 平面上的 width x height 的网格图，左下角 的格子为 (0, 0) ，右上角 的格子为 (width - 1, 
//height - 1) 。网格图中相邻格子为四个基本方向之一（"North"，"East"，"South" 和 "West"）。一个机器人 初始 在格子 (0, 0) ，方
//向为 "East" 。 
//
// 机器人可以根据指令移动指定的 步数 。每一步，它可以执行以下操作。 
//
// 
// 沿着当前方向尝试 往前一步 。 
// 如果机器人下一步将到达的格子 超出了边界 ，机器人会 逆时针 转 90 度，然后再尝试往前一步。 
// 
//
// 如果机器人完成了指令要求的移动步数，它将停止移动并等待下一个指令。 
//
// 请你实现 Robot 类： 
//
// 
// Robot(int width, int height) 初始化一个 width x height 的网格图，机器人初始在 (0, 0) ，方向朝 
//"East" 。 
// void move(int num) 给机器人下达前进 num 步的指令。 
// int[] getPos() 返回机器人当前所处的格子位置，用一个长度为 2 的数组 [x, y] 表示。 
// String getDir() 返回当前机器人的朝向，为 "North" ，"East" ，"South" 或者 "West" 。 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：
//["Robot", "move", "move", "getPos", "getDir", "move", "move", "move", 
//"getPos", "getDir"]
//[[6, 3], [2], [2], [], [], [2], [1], [4], [], []]
//输出：
//[null, null, null, [4, 0], "East", null, null, null, [1, 2], "West"]
//
//解释：
//Robot robot = new Robot(6, 3); // 初始化网格图，机器人在 (0, 0) ，朝东。
//robot.move(2);  // 机器人朝东移动 2 步，到达 (2, 0) ，并朝东。
//robot.move(2);  // 机器人朝东移动 2 步，到达 (4, 0) ，并朝东。
//robot.getPos(); // 返回 [4, 0]
//robot.getDir(); // 返回 "East"
//robot.move(2);  // 朝东移动 1 步到达 (5, 0) ，并朝东。
//                // 下一步继续往东移动将出界，所以逆时针转变方向朝北。
//                // 然后，往北移动 1 步到达 (5, 1) ，并朝北。
//robot.move(1);  // 朝北移动 1 步到达 (5, 2) ，并朝 北 （不是朝西）。
//robot.move(4);  // 下一步继续往北移动将出界，所以逆时针转变方向朝西。
//                // 然后，移动 4 步到 (1, 2) ，并朝西。
//robot.getPos(); // 返回 [1, 2]
//robot.getDir(); // 返回 "West"
//
// 
//
// 
//
// 提示： 
//
// 
// 2 <= width, height <= 100 
// 1 <= num <= 10⁵ 
// move ，getPos 和 getDir 总共 调用次数不超过 10⁴ 次。 
// 
//
// Related Topics 设计 模拟 👍 20 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//Java：模拟行走机器人 II
public class P2069WalkingRobotSimulationIi {
    public static void main(String[] args) {
        // Solution solution = new P2069WalkingRobotSimulationIi().new Solution();
        // TO TEST
        Robot robot = new P2069WalkingRobotSimulationIi().new Robot(8, 2);
        robot.step(17);
        System.out.println(Arrays.toString(robot.getPos()));
        System.out.println(robot.getDir());
        robot.step(21);
        System.out.println(Arrays.toString(robot.getPos()));
        System.out.println(robot.getDir());
        robot.step(22);
        robot.step(34);
        System.out.println(Arrays.toString(robot.getPos()));
        System.out.println(robot.getDir());
        robot.step(1);
        robot.step(46);
        robot.step(35);
        System.out.println(Arrays.toString(robot.getPos()));
        System.out.println(robot.getDir());
        robot.step(44);
        robot.step(14);
        robot.step(31);
        System.out.println(Arrays.toString(robot.getPos()));
        System.out.println(robot.getDir());
        robot.step(50);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Robot {
        int width, height, pathLen;
        int loc = 0;
        // 判断是否移动过，用来判断[0,0]方向
        boolean moved = false;
        String[] dirs = new String[]{"East", "North", "West", "South"};

        public Robot(int width, int height) {
            this.width = width;
            this.height = height;
            this.pathLen = width * 2 + height * 2 - 4;
        }

        public void step(int num) {
            moved = true;
            loc = (loc + num) % pathLen;
        }

        public int[] getPos() {
            if (loc < width - 1) {
                return new int[]{loc, 0};
            } else if (loc < width + height - 2) {
                return new int[]{width - 1, loc - (width - 1)};
            } else if (loc < width * 2 + height - 3) {
                return new int[]{(width - 1) - (loc - (width + height - 2)), height - 1};
            } else {
                return new int[]{0, (height - 1) - (loc - (width * 2 + height - 3))};
            }
        }

        public String getDir() {
            // 刚开始[0,0]方向是 east，但是循环一圈以后变为 south
            if (loc == 0) {
                if (moved) {
                    return "South";
                } else {
                    return "East";
                }
            } else if (loc <= width - 1) {
                return dirs[0];
            } else if (loc <= width + height - 2) {
                return dirs[1];
            } else if (loc <= width * 2 + height - 3) {
                return dirs[2];
            } else {
                return dirs[3];
            }
        }
    }

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */
//leetcode submit region end(Prohibit modification and deletion)

}