package com.example.demo.plugin;

import com.example.demo.domain.Data;
import com.example.demo.job.R6Server;
import lombok.SneakyThrows;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;


/**
 * 示例插件
 * 插件必须继承CQPlugin，上面要 @Component
 * <p>
 * 添加事件：光标移动到类中，按 Ctrl+O 添加事件(讨论组消息、加群请求、加好友请求等)
 * 查看API参数类型：光标移动到方法括号中按Ctrl+P
 * 查看API说明：光标移动到方法括号中按Ctrl+Q
 */
@Component
public class DemoPlugin extends CQPlugin {
    /**
     * 收到私聊消息时会调用这个方法
     *
     * @param cq    机器人对象，用于调用API，例如发送私聊消息 sendPrivateMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件，IGNORE表示继续，BLOCK表示不继续
     */

    String[] me ={"#绑定账号 ID","#R6战绩 ID","#我的战绩"};
    String[] menu ={"#绑定账号","#R6战绩","#我的战绩"};
    @SneakyThrows
    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        String msg = event.getMessage();
        long userId = event.getUserId();
        if (msg.charAt(0)=='#') {

            String fs =  r6(msg,userId);

            cq.sendPrivateMsg(userId, fs, false);
            // 不执行下一个插件
            return MESSAGE_BLOCK;
        }
        // 继续执行下一个插件
        return MESSAGE_IGNORE;
    }


    /**
     * 收到群消息时会调用这个方法
     *
     * @param cq    机器人对象，用于调用API，例如发送群消息 sendGroupMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件，IGNORE表示继续，BLOCK表示不继续
     */
    @SneakyThrows
    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        // 获取 消息内容 群号 发送者QQ
        String msg = event.getMessage();
        long groupId = event.getGroupId();
        long userId = event.getUserId();

        if (msg.charAt(0)=='#') {
            String fs = r6(msg,userId);
            // 回复内容为 at发送者 + hi
            String result = CQCode.at(userId) + fs;

            // 调用API发送消息
            cq.sendGroupMsg(groupId, result, false);

            // 不执行下一个插件
            return MESSAGE_BLOCK;
        }

        // 继续执行下一个插件
        return MESSAGE_IGNORE;
    }

    private String r6(String msg,Long userId) throws Exception {
        String  re = null;
        if(msg.equals("#菜单")){
            String fs;
            fs = String.format("\n1.绑定游戏ID:" +
                    "%s,\n2.查询自己战绩:" +
                    "%s,\n3.查询他人战绩:" +
                    "%s",me[0], me[2], me[1]);
            re =fs;
        }else if(msg.equals(menu[2])){//我的战绩
            Reader r;
            try {

                r=new FileReader("./"+userId+".txt");//.....处写文zd件路径回

            }catch (FileNotFoundException e){

                return "您还未绑定账号";

            }

            BufferedReader br=new BufferedReader(r);
            String s = br.readLine();
            r.close();
            br.close();
            re = R6Server.getData(s);
        }else {

            int index = msg.indexOf(" ");
            String code = null;

            try {

                code = msg.substring(0,index);

            }catch (StringIndexOutOfBoundsException e){

                return "哈麻皮，没这个命令！";

            }

            if(code.equals(menu[0])){//绑定ID

                String gameName = msg.substring(index).trim();

                String uplay_id = R6Server.getUserId(gameName);

                if(uplay_id==null) {

                    return "未找到账号";

                }

                File file =new File("./"+userId+".txt");

                if(!file.exists()){

                    file.createNewFile();

                }
                FileWriter fileWritter = new FileWriter(file.getName());
                BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                bufferWritter.write(uplay_id);
                bufferWritter.close();
                re = "绑定成功";
            }else if (code.equals(menu[1])){//查询战绩

                String gameName = msg.substring(index).trim();

                String uplay_id = R6Server.getUserId(gameName);

                re = R6Server.getData(uplay_id);

            }else {
                re = "哈麻皮，没这个命令！";
            }
        }
        return re;
    }
}
