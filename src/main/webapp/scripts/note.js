/**
 * 封装与笔记note处理有关的脚本
 * @returns
 */
//点击笔记本列表,显示所有笔记
function listNotesAction(){
	
	//$(this)是li对象,就是被点击的li对象
	//获取显示时绑定的序号
	var index = $(this).data('index');
	console.log(index);
	//将选定的笔记本index保存到model中
	model.notebookIndex = index;
	//设置选中笔记本的效果
	//所有a的属性class都清掉
	$('#notebooks li a').removeClass('checked');
	//当前li上的a元素加上class,选中
	$(this).find('a').addClass('checked');
	//console.log(index);
	var url='note/list.do';
	var id = model.notebooks[index].id;
	var param = {notebookId:id};
	//console.log(param);
	$.getJSON(url,param,function(result){
		if(result.state==SUCCESS){
			var notes = result.data;
			//console.log(notes);
			//数据用model显示出来,添加方法updateNotesView()
			model.updateNotesView(notes);
			
		}else{
			alert(result.message);
		}
	});
}
//更新界面,在界面中显示笔记列表信息
model.updateNotesView=function(notes){
	//console.log('updateNoteView');
	if(notes){
		this.notes=notes;
	}
	//给模型增加新属性,存储当前笔记列表
	
	var template='<li class="online"> '+
		            '<a>'+
	                '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>#{title}<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'+
                    '</a>'+
                    '<div class="note_menu" tabindex="-1">'+
	                    '<dl>'+
							'<dt><button type="button" class="btn btn-default btn-xs btn_move"  title="移动至..."><i class="fa fa-random"></i></button></dt>'+
							'<dt><button type="button" class="btn btn-default btn-xs btn_share" id="share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
							'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+
							'<dt><button type="button" class="btn btn-default btn-xs btn_share" id="like" style="background:#f00;color:#000;" title="收藏"><i class="fa fa-star" style="font-size:15px;line-height:10px;"></i></button></dt>'+
						'</dl>'+
					'</div>'+
				'</li> ';
	var ul = $('#notes').empty();
	//遍历笔记数组,将笔记列表显示到界面
	for(var i=0;i<this.notes.length;i++){
		var note = this.notes[i];
		var li = $(template.replace('#{title}',note.title));
	    //将序号绑定到li上
		li.data('index',i);
		//添加选定效果
		if(model.noteIndex==i){
			li.find('a').addClass('checked');
		}
		ul.append(li);
	}
};

function getNoteAction(){
	//console.log('getNoteAction');
	var li = $(this);
	$('#notes li a').removeClass('checked');
	li.find('a').addClass('checked');
	//获取绑定到li上的序号
	var index = li.data('index');
	//console.log(index);
	
	//保存笔记的index到model
	model.noteIndex=index;
	
	var id = model.notes[index].id;
	var url="note/get.do"
	var param = {noteId:id};
	//console.log(param);
	$.getJSON(url,param,function(result){
		if(result.state==SUCCESS){
			 var note  = result.data;
			
			model.updateNote(note);
		}else{
			alert(result.message);
		}
	});
}
model.updateNote=function(note){
	//将笔记的详细信息存储到model中
	this.note = note;
	//将笔记的信息显示到界面中
	//显示笔记标题
	$('#input_note_title').val(this.note.title);
	//显示笔记内容
	//um对象是*富文本编辑器控件**这个控件在edit.html文件的后部初始化,这里直接使用,
	//um对象有两个常用方法,setContent方法用于更新编辑器中的内容,getContent用户获取编辑器中的内容
	um.setContent(this.note.body);
	//获取笔记内容 um.getContent()
}
function saveNoteAction(){
	//console.log('saveNoteAction');
	var url = 'note/save.do';
	//console.log(model.note);
	//服务器上的笔记
	var note = model.note;
	//页面上的title,body
	var title=$('#input_note_title').val();
	var body = um.getContent();
	if(note.title==title && note.body==body){
		model.notes[model.noteIndex].title=title;
		model.updateNotesView();
		//如果没有修改就不保存任何内容了
		return;
	}
	 var param = {id:model.note.id,
			      title:title,
			      body:body
			      };
	 //点击保存按钮 禁用防止再次点击
	 $('#save_note').attr('disabled','disabled').html('保存中..');
	$.post(url,param,function(result){
		//延迟效果
		setTimeout(function(){
			 $('#save_note').removeAttr('disabled','disabled').html('保存笔记');
		},1000);
		
		if(result.state==SUCCESS){
			if(result.data){
				//修改的标题和内容 存到model里
				model.note.title=title;
				model.note.body=body;
				//被选定的note 更新笔记列表title
				model.notes[model.noteIndex].title=title;
				//更新笔记列表区域
				model.updateNotesView();
			}
		}else{
			alert(result.message);
		}
	});
}

function addNoteAction(){
	var url = "note/add.do";
	//console.log(model.notebookIndex);
	var notebookId = model.notebooks[model.notebookIndex].id;
	var	param = {
			notebookId:notebookId,
			userId:getCookie('userId'),
	        title:$('#input_note').val()
	};
	//this指创建按钮
	var btn = $(this).attr('disabled','disabled').html('创建中...');
	$.post(url,param,function(result){
		if(result.state==SUCCESS){
			var note = result.data;
			//console.log(title);
			//在笔记列表数组的第一个位置插入新笔记
			model.notes.unshift({id:note.id,title:note.title});
			//选定第一个笔记
			model.noteIndex=0;
			//更新编辑笔记
			model.updateNote(note);
			//更新笔记列表
			model.updateNotesView();
			//创建延迟 视觉效果
			setTimeout(function(){
				btn.removeAttr('disabled').html('创建');
				 closeAction();
			},800);
		   
		}else{
			alert(result.message);
		}
	});
}
function deleteNoteAction(){
	var url = 'note/delete.do';
	var param = {noteId:model.note.id};
	var btn = $(this).attr('disabled','disabled').html('删除中...');
	$.post(url,param,function(result){
		if(result.state==SUCCESS){
			var title = result.title;
		   //从数组中删除元素
			model.notes.splice(model.noteIndex,1);
			model.updateNotesView();
			showNotesAction();
			setTimeout(function(){
				btn.removeAttr('disabled').html('删除');
				closeAction();
			},800);
		}else{
			alert(result.message);
		}
		
	});
	
}
//显示回收站笔记
function showNotesAction(){
	
	var url = 'note/show.do';
	
	$.getJSON(url,function(result){
		if(result.state==SUCCESS){
			var rnotes = result.data;
			model.updateRollback(rnotes);
		}
	});
}
model.updateRollback=function(rnotes){
	if(rnotes){
		this.rnotes=rnotes;
	}
	var template = '<li class="disable"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> #{title}<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay"><i class="fa fa-reply"></i></button></a></li>';
	var ul = $('#roll_back').empty();
	for(var i=0;i<this.rnotes.length;i++){
		var rnote = this.rnotes[i];
	    var li = $(template.replace('#{title}',rnote.title));
	    li.data('index',li);
	    ul.append(li);
	}
}