/*
 * 初始化脚本
 */
var SUCCESS=0;
var ERROR=1;

//页面中的数据模型对象,存储页面中显示的数据,以及封装更新视图的方法
var model={};
//页面加载以后调用
$(function(){
	//console.log('init OK');
	//加载笔记本列表信息 在notebook.js中定义
	loadNotebooksAction();//加括号()直接执行了
	//监听笔记本的点击事件  在note.js中定义listNotesAction 
	//.on(事件名称,事件源选择器,事件方法)
	//ul上的点击事件过滤到每个li上 带有传播性的用on();
	$('#notebooks').on('click','.notebook',listNotesAction);
    //获取更多笔记本
	$('#notebooks').on('click','.more',loadNotebooksAction);
	
	//监听笔记的点击事件   在note.js中定义getNoteAction
	$('#notes').on('click','li',getNoteAction);
	
	//在note.js中定义
	$('#save_note').click(saveNoteAction);
  
    //监听关闭窗口事件
	$('#can').on('click','.close,.cancle',closeAction);
    //显示笔记本对话框
    $('#add_notebook').click(showNotebookDialogAction);
    //显示笔记对话框
    $('#add_note').click(showNoteDialogAction);
    //监听笔记子菜单,显示出来
    $('#notes').on('click','.btn_slide_down',showNoteMenuAction);
     //点任意位置,关闭笔记子菜单
    $('body').click(function(){
    	$('#notes .note_menu').hide();
    });
    //监听删除按钮被点击了
    $('#notes').on('click','.btn_delete',showDeleteNoteDialogAction);
    //加载回收站笔记   在note.js中定义
    $('#rollback_button').click(loadRollbackNotesAction);
});

function loadRollbackNotesAction(){
	$('#pc_part_4').toggle();
	showNotesAction();
}

function showDeleteNoteDialogAction(){
	var title =model.note.title;
	$('#can').load('alert/alert_delete_note.html',function(){
		$('#can p').html('笔记<strong>'+title+'</strong>将被删除至<strong>回收站</strong>，是否继续删除？');
		$('.opacity_bg').show();
		$('#can .sure').click(deleteNoteAction);
	});
}


function showNoteMenuAction(){
	//toggle()有则消失,无则加之
	$(this).parent().next().toggle();
	//事件阻止,事件不再向下传播, $('body')接收不到事件
	return false;
}


//显示笔记对话框
function showNoteDialogAction(){
	//没有笔记本,不能创建笔记
	if(!model.notebooks[model.notebookIndex]){
		return;
	}
	$('#can').load('alert/alert_note.html',function(){
		$('.opacity_bg').show();
		//在note.js中定义
		$('#can .sure').click(addNoteAction);
	});
}


//显示笔记本对话框
function showNotebookDialogAction(){
	
	$('#can').load('alert/alert_notebook.html',function(){
		$('.opacity_bg').show();
		//在notebook.js中定义
		$('#can .sure').click(addNotebookAction);
	});
}
//监听关闭窗口事件
function closeAction(){
	
	$('#can').empty();
	$('.opacity_bg').hide();
}