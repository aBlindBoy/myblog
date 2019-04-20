/**
 * @license Copyright (c) 2003-2018, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here.
	// For complete reference see:
	// http://docs.ckeditor.com/#!/api/CKEDITOR.config

	// The toolbar groups arrangement, optimized for two toolbar rows.
	config.toolbarGroups = [
		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
		{ name: 'links' },
		{ name: 'insert' },
		{ name: 'forms' },
		{ name: 'tools' },
		{ name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'others' },
		'/',
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
		{ name: 'styles' },
		{ name: 'colors' },
		{ name: 'about' }
	];
	//删除预览
	//config.image_previewText="";
	//config.removeButtons = 'Underline,Subscript,Superscript';
	
	// 换行方式
	//config.enterMode = CKEDITOR.ENTER_BR;
	// 当输入：shift+Enter是插入的标签
//	config.shiftEnterMode = CKEDITOR.ENTER_BR;//
	//图片处理
	//config.pasteFromWordRemoveStyles = true;
	// 去掉ckeditor“保存”按钮
//	config.removePlugins = 'save'
	config.filebrowserImageUploadUrl = '/back/article/uploadFile';//上传图片保存路径
	config.filebrowserUploadUrl = '/back/article/uploadFile';//上传文件保存路径
	//config.filebrowserFlashUploadUrl = '/back/article/uploadFile';//上传flash保存路径


	// 文本框高度
	config.height = 500;
	// 背景颜色
	config.uiColor = '#E6E6FA';
};
