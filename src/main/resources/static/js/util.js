/**
 * Created by pengxianyang on 2018/4/8.
 */

/**
 * 将 $("form").serialize() 序列化之后的字符串转换为json
 * @param str
 */
function serializeToJSON(str){
    if(!(str || '')){
        return null;
    }
    var obj = {};
    var attrArr = str.split("&");
    for(i in attrArr){
        var key = attrArr[i].split("=")[0];
        var value = attrArr[i].split("=")[1];

       if(obj['"'+key+'"']){
           if(!(obj['"'+key+'"'] instanceof Array)){//如果不是数组，则初始化
               var oldValue = obj['"'+key+'"'];
               obj['"'+key+'"'] = [];
               obj['"'+key+'"'].push(oldValue);
           }
           obj['"'+key+'"'].push(value);
        }else{
            obj['"'+key+'"'] = value;
        }
    }
    return JSON.stringify(obj);
}