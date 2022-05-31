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
        content[0] = "宜: 结婚 搬新房 动土 祈福 栽种 答辩满分";
        content[1] = "忌：开仓 掘井 开光";
        break;
      case 2:
        content[0] = "宜: 打扫房屋 清洁 馀事 勿取 答辩满分";
        content[1] = "忌：诸事不宜";
        break;
      case 3:
        content[0] = "宜：出行 签订合同 交易 搬新房 开业 动土";
        content[1] = "忌：结婚 安床 作灶 探病";
        break;
      case 4:
        content[0] = "宜：结婚 出行 签订合同 交易 开业 祈福";
        content[1] = "忌：搬家 搬新房 动土 纳畜";
        break;
      case 5:
        content[0] = "宜：打扫 破屋 祭祀 馀事 勿取 坏垣 答辩满分";
        content[1] = "忌：诸事不宜";
        break;
      case 6:
        content[0] = "宜：出行 打扫  祭祀 入殓 答辩满分";
        content[1] = "忌：结婚 搬新房 动土 架马 修造";
        break;
      case 7:
        content[0] = "宜：房屋清洁 沐浴 移柩 馀事 答辩满分";
        content[1] = "忌：诸事不宜";
        break;
      case 8:
        content[0] = "宜：会亲友 合婚 订婚 订盟 稠作灶 答辩满分";
        content[1] = "忌：开业安葬";
        break;
      case 9:
        content[0] = "宜：合婚 订婚 订盟 动土 祈福 答辩满分";
        content[1] = "忌：搬家 搬新房";
        break;
      case 0:
        content[0] = "宜：结婚 搬新房 买衣服 安床 答辩满分";
        content[1] = "忌：赴任 除虫";
        break;
      default:
        content[0] = "宜：结婚 出行 搬家 搬新房 答辩满分";
        content[1] = "忌：赴任 除虫";
    }

    return content;
  }
}
