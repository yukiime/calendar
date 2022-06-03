package team.utils;

public class DateEvent {

    /**
     * 调用后返回今日的运势信息
     *
     * @return content 运势的文本信息
     */
    public static String[] todayFortune(int solarDate) {
        String[] content = new String[2]; // 运势的文本信息
        int caseCode = solarDate % 10;

        switch (caseCode) {
            case 1:
                content[0] = "宜：结婚 搬新房 祈福 栽种";
                content[1] = "忌：开仓 掘井 开光 勿取";
                break;
            case 2:
                content[0] = "宜：打扫房屋 清洁 馀事 开业";
                content[1] = "忌：勿取 坏垣 作灶 探病";
                break;
            case 3:
                content[0] = "宜：答辩 出行 签订合同 交易";
                content[1] = "忌：结婚 安床 作灶 探病";
                break;
            case 4:
                content[0] = "宜：结婚 出行 签订合同 祈福";
                content[1] = "忌：搬家 搬新房 动土 纳畜";
                break;
            case 5:
                content[0] = "宜：打扫 破屋 祭祀 勿取 ";
                content[1] = "忌：答辩给低分 修造 动土";
                break;
            case 6:
                content[0] = "宜：答辩满分 出行 打扫 祭祀";
                content[1] = "忌：结婚 搬新房 架马 修造";
                break;
            case 7:
                content[0] = "宜：房屋清洁 沐浴 移柩 馀事";
                content[1] = "忌：入殓 安葬 订盟 买衣服";
                break;
            case 8:
                content[0] = "宜：会亲友 合婚 订婚 订盟";
                content[1] = "忌：开业 安葬 掘井 开光";
                break;
            case 9:
                content[0] = "宜：合婚 订婚 订盟 祈福";
                content[1] = "忌：搬家 搬新房 安床 开仓";
                break;
            case 0:
                content[0] = "宜：结婚 搬新房 买衣服 安床";
                content[1] = "忌：赴任 除虫 作灶 探病";
                break;
            default:
                content[0] = "宜：结婚 出行 搬家 答辩满分";
                content[1] = "忌：赴任 除虫 搬家 搬新房";
        }

        return content;
    }
}
