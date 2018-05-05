/*
 * 封装与笔记本有关的脚本
 */

function loadNotebooksAction(){
	//console.log('loadNotebooksAction()');
	//发起AJAX请求 获取笔记本列表数据
	var url = "notebook/list0.do";
	if(!model.notebooks){
		model.pageNum=0;
	}else{
		model.pageNum++;
	}
	var param = {userId:getCookie('userId'),pageNum:model.pageNum};
	$.getJSON(url,param,function(result){
		if(result.state==SUCCESS){
			var notebooks = result.data;
			//console.log(notebooks);
			//调用模型更新笔记本列表功能
			model.updateNotebooksView(notebooks);
		}
	});
}
/**
 * 模型中的更新视图方法,当收到笔记本数据时更新界面的显示内容
 * 
 */
//为model增加新方法updateNotebooksView,匿名方法体function(){}, notebooks参数
model.updateNotebooksView=function(notebooks){
	//console.log('updateNotebooksView');
	//this 就是当前对象model
	//为model增加新属性notebooks
	if(!this.notebooks){
		this.notebooks = notebooks;
	}else{
		//追加后面的笔记本,数组追加
		this.notebooks=this.notebooks.concat(notebooks);
	}
	
	//console.log(model);
	var ul =$('#notebooks').empty();
	//定义每个笔记本的显示模板
	var template ='<li class="online notebook"> '+
	              '<a>'+
	              '<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+ 
	              '#{name}</a></li>';
	//遍历每个notebook对象,显示到笔记本区域
	for(var i=0;i<this.notebooks.length;i++){
		var notebook=this.notebooks[i];
		//替换模板中的标记#{name}  加$,li变成jQuery对象
		var li =$(template.replace('#{name}',notebook.name));
		//data()是jQuery对象提供的数据绑定方法,在每个li元素上绑定了一个位置序号index
		li.data('index',i);
		ul.append(li);
	}
	//for循环之后增加个<li> 为区别笔记本li 加类more
	var li ='<li class="online more"><a>More...</a></li>';
	ul.append(li);
};

function addNotebookAction(){
	console.log('addNotebookAction');
}




