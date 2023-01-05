package simple_rpc.IDL.Hello;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
//序列化方便对象存储和远程传输(对象流与字节流转换)
public class HelloResponse implements Serializable {
    private String msg;
}
