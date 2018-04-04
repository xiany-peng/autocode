var prefix = '/generator'
$(function(){
   loadTable();
});

function loadTable(){
    $('#exampleTable').bootstrapTable({
       method : 'post',
       url : prefix + "/list",
       striped : true,
       dataType : "json", 
       pagination : true,
       toolbar : '#exampleToolbar',
       pageSize : 10,
       pageNumber : 1,
       search : true,
       showColumns : true,
       sidePagination : "client",
        columns : [
              {
                  checkbox : true
              },
              {
                  field : 'tableName',
                  title : '表名'
              },
              {
                  field : 'comment',
                  title : '注释'
              },
              {
                  field : 'createTime',
                  title : '创建时间'
              },
              {
                  title : '操作',
                  field : 'roleId',
                  align : 'center',
                  formatter : function(value, row, index) {
                     return [
                         '<a class="generator btn btn-primary btn-sm" href="#"  title="生成">',
                         '<span class="fa fa-download"></span>',
                         '</a> '
                     ].join("");
                  },
                  events : {
                      'click a.generator' : function(e,value,row,index){
                          location.href = prefix + "/code/" + row.tableName;
                      }
                  }
              } ]
      });
}

function batchCode(){
    var rows = $("#exampleTable").bootstrapTable('getSelections');
    if(rows.length == 0 ){
        layer.msg("请选择需要生成代码的表");
        return;
    }
    var tables = new Array();
    $.each(rows,function(i,v){
        tables.push(v.tableName);
    });
    location.href = prefix + "/batchCode?tables="+JSON.parse(tables);
}

function generatorConfig(){
    layer.open({
        type : 2,
        title : '配置',
        maxmin : true,
        shadeClose : false,
        area : [ '90%', '90%' ],
        content : '/config/generator'
    });
}