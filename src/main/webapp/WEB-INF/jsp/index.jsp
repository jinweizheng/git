<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="static/css/bootstrap.css" />
<link rel="stylesheet" href="static/css/zTreeStyle.css" />

<title>首页</title>
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
</head>
<body>


<div class="container-fluid">

<div class="row">
	<div class="col-sm-3" style="background-color: #000000">
		<h2 style="color: white;">LOGO</h2>
	</div>
	<div class="col-sm-9" style="background-color: #000000">
		<h2 style="color: white;">镇江极客营</h2>
	</div>
</div>
<div class="row"   style="margin-top: 10px">
	<div class="col-sm-offset-4">
		
		<form class="form-inline">
		<div class="form-group">
    	名称:
    	<input type="text" class="form-control sname">
  		<button type='button' class='btn btn-primary btn-md query'>查找</button>
  		
  		</div>

    	</form>
 	 </div>
 
	</div>
	
</div>

 <div class="row">
 		
 	<div class="col-sm-3" >
 		<div id="tree" class="ztree"></div>
 	</div>
 		
 	<div class="col-sm-9" style="margin-top:10px;background-color: #E6E6E6">
 	
 	<table id=""class="table">
      <thead>
          <tr >
              <th>ID</th>
              <th>姓名</th>
              <th>年龄</th>
              <th>操作</th>
          
          </tr>
      </thead>
      <tbody id="student">
     
      </tbody>
      <tfoot>
      <tr>
      <td colspan="3">
      </td>
       <td colspan="1">
      	<button type='button' class='btn btn-primary btn-md save'>添加</button>
      </td>
      </tr>
      </tfoot>
</table>
 	
<div id="Paginator" style="text-align: center"> <ul id="pageLimit"></ul> </div>
 	
 </div>
 </div>





<script type="text/javascript" src="static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="static/js/bootstrap.js" ></script>
<script type="text/javascript" src="static/js/bootstrap-paginator.min.js" ></script>
<script type="text/javascript" src="static/js/jquery.ztree.core.min.js" ></script>
<script type="text/javascript" src="static/js/jquery.ztree.exedit.js" ></script>
<script type="text/javascript" src="static/js/jquery.ztree.excheck.js" ></script>

<script type="text/javascript">

var  jquery=function() {
	
	var self=this;
	var pages={
        pageNum:1,
        pageSize:10,
        name:$(".sname").val()
    }
	this.init =function(){
		
		self.loadTree();
		self.loadStudent(pages);
		self.evens();
	}
	
	this.loadStudent=function(data){
		
		$.ajax({ 
			url: "/student",
			type:"GET",
			data:data,
			success: function(e){
	       	
	       		var html="";
	       		
	       		for (x in e.list) {
	       			
					html+="<tr><td>"+e.list[x].id+"</td><td>"+e.list[x].name+"</td><td>"+e.list[x].age+"</td>"
					+"<td><button type='button' class='btn btn-primary btn-xs update' value='"+e.list[x].id+"'>编辑</button>"
					+"<button type='button' class='btn btn-danger btn-xs del' value='"+e.list[x].id+"'>删除</button>"
					+"</td></tr>"
				}
				$("#student").html(html);
		
				self.loadEvens();
				self.page(e);
				
	      }});
		
	}
	
	this.loadTree=function(date){
	
	
		var setting = {
				 view: {  
					addHoverDom: addHoverDom,
					removeHoverDom: removeHoverDom,//当鼠标移动到节点上时，显示用户自定义控件      
				
					},  
					check: {
						enable: true,
						chkStyle: "radio",
						chkboxType: { "Y": "p", "N": "s" }
					},
				async: {
					enable: true,
					url: "/dict/tree",
					autoParam: ["id=parentId"]
				},
				 edit: {
		                enable: true,
		                editNameSelectAll:true,
		                removeTitle:'删除',
		                renameTitle:'重命名'
		            },
				callback: {
					beforeRemove:beforeRemove,
					onClick: zTreeOnClick,
					onRename: zTreeOnRename,
					onRemove: onRemove, //移除事件
				}
				
			};
	
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='add node' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				var zTree = $.fn.zTree.getZTreeObj("tree");
				$.ajax({ 
					url: "/dict",
					type:"post",
					data:{
						dname:"新节点",
						parentId:treeNode.id,
						dtype:"address",
					},
					success: function(e){
						
					zTree.addNodes(treeNode, {id:e, pId:treeNode.id, name:"新节点"});
						
			      }});
				return false;
			});
		};
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove();
		};
		function beforeRemove(e,treeId,treeNode){
	        return confirm("你确定要删除吗？");
	    }
		
		function onRemove(e, treeId, treeNode) {
		 
			$.ajax({ 
				url: "/dict/"+treeNode.id,
				type:"delete",
				success: function(e){
				
					
		      }});
		}
		function zTreeOnRename(event, treeId, treeNode, isCancel) {
	
			$.ajax({ 
				url: "/dict",
				type:"put",
				data:{
					did:treeNode.id,
					dname:treeNode.name,
				},
				success: function(e){
				
					
		      }});
		}
		$.fn.zTree.init($("#tree"), setting);
		function zTreeOnClick(event, treeId, treeNode) {
    	 
    		var info={
	    	        pageNum:1,
	    	        pageSize:10,
	    	        did:treeNode.id
	    	  }
    	
            self.loadStudent(info);
    	};
    
    	
	}
	
	this.page=function(e){
		$('#pageLimit').bootstrapPaginator({    
		    currentPage: e.pageNum,    
		    totalPages:  e.total,    
		    size:"normal",    
		    bootstrapMajorVersion: 3,    
		    alignment:"right",    
		    numberOfPages:10,    
		    itemTexts: function (type, page, current) {        
		        switch (type) {            
		        case "first": return "首页";            
		        case "prev": return "上一页";            
		        case "next": return "下一页";            
		        case "last": return "末页";            
		        case "page": return page;
		        }
		    },
		    onPageClicked: function (event,originalEvent,type,page) {
        		
        		
		    	var info={
		    	        pageNum:page,
		    	        pageSize:10,
		    	        name:$(".sname").val()
		    	  }
		    
                self.loadStudent(info);
            }
		});
		
	}
    this.loadEvens=function(){
    		
    	$(".del").click(function(){
    		
    		var that=$(this);
    		$.ajax({ 
    			url: "/student/"+that.val(),
    			type:"delete",
    			success: function(e){
    	       	
    	       	console.log("ok")
    	      }});
    		
    	})
    	
    	$(".save").click(function(){
    		
    		
    		var treeObj = $.fn.zTree.getZTreeObj("tree");
    		var nodes = treeObj.getCheckedNodes(true);
    		if(nodes.length!=1){
    			 return confirm("只能同时选中一个节点添加？");
    		}
    		
    	})
    	
    	$(".update").click(function(){
    		
    		
    		var that=$(this);
    		var sib=that.parent().siblings()
    		if(that.text()=="编辑"){
    			for (var i = 0; i < sib.length; i++) {
    				$(sib[i]).html("<input type='text' value='"+$(sib[i]).text()+"'>");
    			}
    			that.text("保存");
    			return ;
    		}
    		if(that.text()=="保存"){
    		
    			for (var i = 0; i < sib.length; i++) {
    			
    				$(sib[i]).text($(sib[i]).children().val());
    			}
    			var data={
    					id:that.val(),
        				name:that.parent().prev().prev().text(),
        				age:that.parent().prev().text(),	
    			}
    		
    			$.ajax({ 
        			url: "/student",
        			type:"put",
        			data:data,
        			success: function(e){
        	       	
        				that.text("编辑");
        	       		
        	      }});
    			
    		}
    		
    		
    	})
    	
    	
    }
    this.evens=function(){
   		$(".query").click(function(){
   			var info={
   	    	        pageNum:1,
   	    	        pageSize:10,
   	    	       	name:$(".sname").val()
   	    	  }
   		
   	        self.loadStudent(info);
   		})
    	
    }
   
};

var jquery =new jquery();
jquery.init();


</script>
</body>
</html>