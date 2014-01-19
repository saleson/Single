(function($){
	var kendo = window.kendo,
	proxy = $.proxy,
	Widget = kendo.ui.Widget,
	DESKTOP = "desktop",
	APPLISTCONTAINER = "appListContainer",
	APPBUTTON = "appButton",
	APPBUTTON_APPICON = "appButton_appIcon",
	APPBUTTON_APPICONIMG = "appButton_appIconImg",
	APPBUTTON_APPNAME = "appButton_appName",
	APPBUTTON_APPNAME_INNER = "appButton_appName_inner",
	APPBUTTON_APPNAME_INNER_RIGHT = "appButton_appName_inner_right";
	
	var DeskTop = Widget.extend({
		init: function (element, options){
			var that = this;
			Widget.fn.init.call(that, element, options);
			that.createDeskTop();
			that.appContainerList = [];
		},
		
		/**
		 * auto: {
		 * 	
		 * } 自动创建任务栏,和应用面板的参数
		 */
		options: {
            name: "DeskTop"
        }, 
        
        createDeskTop: function (){
        	var that = this,
        	ele = that.element;
        	ele.css("overflow", "hidden").addClass(DESKTOP);
        },
        
        addAppListContainer: function (container){
        	var that = this;
        	that.appContainerListp[that.appContainerListp.length + 1] = container;
        	container.element.appendTo(that.element);
        }
		
	});
	kendo.ui.plugin(DeskTop);
	
	
	/**
	 * 应用的DIV容器(面板)
	 */
	var AppListContainer =  Widget.extend({
		/**
		 * 
		 * @param element
		 * @param options {appendTo: 组件的父节点}
		 */
		init: function (element, options){
			var that = this;
			Widget.fn.init.call(that, element, options);
			that.desktop = options.desktop;
			that.createContainer();
			that.appMap = {};
			that.appLength = 0;
			that.rows = 1;
			that.currentIdex = 0;
			that.initializeContainer();
			
			$(window).resize(proxy(that.pageResizeHandler, that));
			//document.body.onresize = that.pageResizeHandler;
		}, 
		
		/**
		 * desktop: 桌面组件
		 * gap: 间隔. top,left是跟容器的距离;width,heigth是按钮之前的距离
		 * taskContainer: 任务栏容器
		 * appButtons: 按钮的参数, [{}] List<Map>类型
		 */
		options: {
            name: "AppListContainer",
            gap:{top: 12, left: 27, width: 54, height: 24}
        },
        
        createContainer: function(){
        	var that = this,
        		ele = that.element,
        		appendTo = that.desktop && that.desktop.element || $(document.body);
        	ele.appendTo(appendTo).addClass(APPLISTCONTAINER);
        },
        
        initializeContainer: function (){
        	var that = this,
        	options = that.options,
        	appButs = options.appButtons;
        	
        	for(var i=0;i<appButs.length;i++){
        		that.addApp(appButs[i]);
        	}
        },
        
        /**
         * 添加一个应用到桌面面板.
         * @param options 
         */
        addApp: function(options){
        	var that = this,
        	appid = options.app.id;
        	var appButEle = $("<div id=appButton_"+appid+"></div>").appendTo(that.element);
        	options.container = that;
        	var appButton = appButEle.kendoAppButton(options).data("kendoAppButton");
        },
        
        /**
         * 添加一个按钮到桌面.
         * @param appButton
         */
        addAppButton: function(appButton){
        	var that = this,
        	buttonEle = appButton.element;
        	that.element.append(buttonEle);
        	app = appButton.app;
        	that.appMap[app.id] = appButton;
        	that.appLength++;
        	that.setButtonTopLeft(appButton);
        },
        
        /**
         * 设置按钮的X,Y坐标.
         * @param appButton
         */
        setButtonTopLeft: function (appButton){
        	var that = this,
        	ele = that.element,
        	gap = that.options.gap,
        	butEle = appButton.element,
        	butWidth = butEle.width(), 
    		butHeight = butEle.height(),
    		containerWidth = ele.width(),
    		containerHeight = ele.height();
        	
        	var left = gap.left + ((gap.width + butWidth) * (that.rows - 1));
        	var top = gap.top + ((gap.height + butHeight) * that.currentIdex);
        	var needHeight = top + butHeight, 
        		needWidth = left + butWidth;
        	if(needHeight > containerHeight && needWidth > containerWidth){
        		that.resizeHandler();
        		that.setButtonTopLeft(appButton);
        	}else if(needHeight > containerHeight){
        		that.rows++;
        		that.currentIdex = 0;
        		that.setButtonTopLeft(appButton);
        	}else {
        		butEle.css({top: top, left : left});
        		that.currentIdex++;
        	}
        },
        
        resizeHandler: function (){
        	
        	
        }
	});
	kendo.ui.plugin(AppListContainer);
	
	
	
	/**
	 * 应用按钮(应用的接口)
	 */
	var AppButton = Widget.extend({
		
		/**
		 * 初始化的方法.
		 * @param element
		 * @param options
		 */
		init: function (element, options){
			if(!options.app || !options.app.id){
				throw new $.error("app.id is empty or undefined!");
			}
			var that = this;
			Widget.fn.init.call(that, element, options);
			that.app = options.app;
			that.create();
		},
		
		/**
		 * container: 应用按钮的容器, 
		 * app: {id, name} 应用的ID\NAME, 
		 * window: 创建窗口的参数(object对象 -- {})
		 */
		options: {
            name: "AppButton"
        },
        
        /**
         * 创建的方法.
         */
        create: function (){
        	var that = this,
        	ele = that.element,
        	app = that.app,
        	options = that.options,
        	container = that.container = options.container;
        	
        	ele.addClass(APPBUTTON)
        	.append("<div class=\""+APPBUTTON_APPICON+"\"><img alt=\""+app.name+"\" src=\"framework/images/icon/"+app.id+"-big.png\"class=\""+APPBUTTON_APPICONIMG+"\"></div>"
        		   +"<div class=\""+APPBUTTON_APPNAME+"\">"	
        		   +"<div class=\""+APPBUTTON_APPNAME_INNER+"\">"+app.name+"</div><div class=\""+APPBUTTON_APPNAME_INNER_RIGHT+"\"></div>"
        		   +"</div>"
        	).on("click", proxy(that.click, that));
        	container.addAppButton(that);
        },
        
        /**
         * 单击的事件.
         */
        click: function (){
        	var that = this,
        	options = that.options,
        	app = that.app;
        	var appWinId = "appWindow_" + app.id,
        	appWin = $("#"+appWinId);
        	if(appWin.length==0){
        		appWin = $("<div id=\""+appWinId+"\" style=\"overflow: hidden\"></div>").appendTo("body");
        		var winOptions = $.extend(options.window, {app: app, title: app.name});
        		if(!winOptions.taskContainer)
        			$.extend(winOptions, {taskContainer: that.container.options.taskContainer});
        		appWin.kendoAppWindow(winOptions);
        	}else{
        		appWin.data("kendoAppWindow").open();
			}
        },
        
        /**
         * 销毁的方法.
         */
        destroy: function (){
        	var that = this;
        	that.container.removeTaskItem(that.app.id);
        	Widget.fn.destroy.call(that);
            kendo.destroy(that.element);
            that.element.remove();
        }
	});
	kendo.ui.plugin(AppButton);
	
})(window.kendo.jQuery);


(function($){
	var kendo = window.kendo,
	Window = kendo.ui.SingleWindow,
	KWINDOW = ".k-window";
	/**
	 * 应用窗口组件
	 */
	var AppWindow = Window.extend({
		
		/**
		 * 
		 * @param element
		 * @param options {task:{taskContainer: 任务栏组件}; app: {id: 应用的ID, aname: 应用的名称}}
		 */
		init: function(element, options){
			if(!options.app || !options.app.id){
				throw new $.error("app.id is empty or undefined!");
			}
			var that = this;
			Window.fn.init.call(this, element, options);
			
			var app = that.app = that.options.app,
			wrapper = that.wrapper,
			taskContainer = options.taskContainer;
			
			if(taskContainer){
				var taskItem = $("<div id=\"taskGroup_"+app.id+"\"></div>").kendoTaskItem({
						container: taskContainer,
						window: that
					}).data("kendoTaskItem");
				/*wrapper.add(wrapper.find(".k-resize-handler,.k-window-titlebar"))
				.on("mousedown", function(){
					taskContainer.setCurrentTaskItem(app.id);
				});*/
			}
		},
		 
        options: {
            name: "AppWindow", 
            appid: ""
        },
        
        /**
         * 打开窗口
         */
        open: function (){
        	var that = this;
        	Window.fn.open.call(this);
        	that.setTaskItemSelected();
        },
        
        /**
         * 将窗口放到最前面.
         * @param e event 对象
         */
        toFront: function (e) {
        	var that = this,
        	target = $(e && e.target);
        	Window.fn.toFront.call(that, e);
        	if(!target.hasClass("k-i-minimize") && !target.hasClass("k-i-close")){
        		that.setTaskItemSelected();
        	}
        	var console = $("#console").append("<p>k-i-minimize:"+target.hasClass("k-i-minimize")+"   ===   k-i-close:"+target.hasClass("k-i-close")+"</p>");
        	console.scrollTop(console.get(0).scrollHeight);
        },
        
        /**
         * 设置任务栏的当前选中的任务项.
         */
        setTaskItemSelected: function (){
        	var that = this,
        	app = that.app,
        	taskItem = that.taskItem;
        	if(taskItem && taskItem.container){
        		taskItem.container.setCurrentTaskItem(app.id);
        	}
        },
        
        /**
         * 创建窗口的方法.
         */
        _createWindow: function(){
        	var that = this, wrapper, options = that.options;
        	Window.fn._createWindow.call(that);
        	wrapper = that.element.closest(KWINDOW);
        	wrapper.addClass("AppWindow");
        	if(!options.appid)
        		options.appid = new Date().getTime();
        	that.createTime = new Date().getTime();
        }, 
        
        destroy: function (){
        	var that = this, taskItem = that.taskItem;
        	taskItem.destroy();
        	Window.fn.destroy.call(this);
        }
    });
	kendo.ui.plugin(AppWindow);
})(window.kendo.jQuery);

/**
 * 任务栏组件
 */
(function($){
	var kendo = window.kendo,
	proxy = $.proxy,
	Widget = kendo.ui.Widget,
	_P = ".",
	BOTTOMBARBGTASK = "bottom-bar-bg-task",
	BOTTOMBAR = "bottom-bar",
	TASKCONTAINER = "task-container",
	TASKCONTAINERINNER = "task-container-inner",
	TASKGROUP = "task-group",
	TASKITEMBOX = "task-item-box",
	TASKCURRENT = "task-current",
	TASKITEMICON = "task-item-icon",
	TASKITEMTXT = "task-item-txt",
	TASKPREBOX = "task-previous-box",
	TASKPRE = "task-previous",
	TASKPREVIOUSDISABLE = "task-previous-disable",
	TASKNEXTDISABLE = "task-next-disable",
	TASKNEXTBOX = "task-next-box",
	TASKNEXT = "task-next";
	/**
	 * 任务栏组件
	 */
	var TaskContainer = Widget.extend({
		/**
		 * 
		 * @param element
		 * @param options {appendTo: 组件的父节点}
		 */
		init: function (element, options){
			var that = this;
			Widget.fn.init.call(that, element, options);
			that.desktop = options.desktop,
			that._createTaskContainer();
			that.taskMap = {};
			that.taskLength = 0;
			$(window).resize(proxy(that.resizeHandler, that));
		}, 
		
		/**
		 * desktop: 桌面组件
		 */
		options: {
            name: "TaskContainer"
        },
		
        /**
         * 创建
         */
		_createTaskContainer: function (){
			var that = this,
			contentHtml = that.element,
			appendTo = that.desktop && that.desktop.element || $(document.body);
			$("<div class=\""+BOTTOMBARBGTASK+"\"></div>").appendTo(appendTo);//底部栏
			
			var _c_id;
			if(!(_c_id = contentHtml.attr("id")))
				contentHtml.attr("id", (_c_id = new Date().getTime()));
			var taskConInnerId = _c_id + "_inner", 
				bottomBarId = _c_id + "_bottomBar", 
				taskPreBoxId =  _c_id + "_taskPreBox",
				taskPreId = _c_id + "_taskPre";
				taskNextBoxId =  _c_id + "_taskNextBox",
				taskNextId = _c_id + "_taskNext";
			
			var taskConInner = that.taskContainerInner = $("<div id=\""+taskConInnerId+"\" class=\""+TASKCONTAINERINNER+"\" style=\"margin-right: 0px; \"></div>");
			contentHtml.addClass(TASKCONTAINER)
			.append(taskConInner)
			.on("click", proxy(that.innerClick, that));
			
			var wrapper = that.wrapper = $("<div class=\""+BOTTOMBAR+"\" id=\""+bottomBarId+"\" style=\"z-index: 12; \">");
			var taskPreBox = $("<div id=\""+taskPreBoxId+"\" class=\""+TASKPREBOX+"\" style=\"display: none;\">"
			            	  + "<a hidefocus=\"true\" id=\""+taskPreId+"\" class=\""+TASKPRE+"\" href=\"#\"></a>"
							  + "</div>");
			
			var taskNextBox = $("<div id=\""+taskNextBoxId+"\" class=\""+TASKNEXTBOX+"\" style=\"display: none;\">"
								+ "<a hidefocus=\"true\" id=\""+taskNextId+"\" class=\""+TASKNEXT+"\" href=\"#\"></a>"
								+ "</div>");
			wrapper
            .appendTo(appendTo)
            .append(taskNextBox)
            .append(contentHtml)
            .append(taskPreBox);
			taskPreBox.on("click", proxy(that.taskToPrevious, that));
			taskNextBox.on("click", proxy(that.taskToNext, that));
			that.taskPreBox = taskPreBox;
			that.taskNextBox = taskNextBox;
		},
		
		/**
		 * 显示前面的任务项.
		 */
		taskToPrevious: function (){
			var that = this,
			taskPre = that.taskPreBox.find("a");
			if(!taskPre.hasClass(TASKPREVIOUSDISABLE)){
				var element = that.element,
				_inner = that.taskContainerInner,
				taskItemWidth = element.find(_P+TASKCURRENT).width(),
				maxContainerWidth = _inner.width(),
				containerWidth = element.width();
				var marginRight = _inner.css("marginRight");
				marginRight = !!marginRight 
					? (marginRight = (marginRight.replace("px", "") *1) - taskItemWidth) - containerWidth < -1 * maxContainerWidth 
							? containerWidth - maxContainerWidth : marginRight : -1 * taskItemWidth;
				_inner.css("marginRight", marginRight);
				if((marginRight - containerWidth) == (-1 * maxContainerWidth)){
					that.taskPreBox.find("a").addClass(TASKPREVIOUSDISABLE);
				}
				that.taskNextBox.find("a").removeClass(TASKNEXTDISABLE)
			}
		},
		
		/**
		 * 显示后面的任务项.
		 */
		taskToNext: function (){
			var that = this,
			taskNext = that.taskNextBox.find("a");
			if(!taskNext.hasClass(TASKNEXTDISABLE)){
				var element = that.element,
				_inner = that.taskContainerInner,
				taskItemWidth = element.find(_P+TASKCURRENT).width(),
				maxContainerWidth = _inner.width(),
				containerWidth = element.width();
				var marginRight = _inner.css("marginRight");
				marginRight = !marginRight ? 0 : 
						(marginRight = (marginRight.replace("px", "") *1) + taskItemWidth) > 0 ?  0 : marginRight;
				_inner.css("marginRight", marginRight);
				if(marginRight==0){
					taskNext.addClass(TASKNEXTDISABLE)
				}
				that.taskPreBox.find("a").removeClass(TASKPREVIOUSDISABLE);
			}
		},
		
		destroy: function (){
        	var that = this,
        	taskMap = that.taskMap;
        	Widget.fn.destroy.call(that);
        	for(var k in taskMap){
        		var taskItem = taskMap[k];
        		taskItem.destroy();
        	}
        	that.taskMap = {};
        	that.taskLength = 0;
            kendo.destroy(that.wrapper);
            that.wrapper.remove();
        },
		
        /**
         * 任务栏单击事件触发的函数.
         * @param e event对象.
         */
		innerClick: function (e){
			var that = this,
			_taskItemDiv = $(e.target).closest(_P + TASKGROUP), taskItem;
			if(_taskItemDiv.length>0 && !!(taskItem=_taskItemDiv.data("kendoTaskItem"))) {
				taskItem.taskClick();
			}
		},
		
		/**
		 * 添加任务项组件
		 * @param taskItem	任务项组件
		 * @param appId		应用ID
		 */
		addTaskItem: function (taskItem, appId){
			var that = this;
			inner = that.element.find(_P + TASKCONTAINERINNER).append(taskItem.element),
			element = that.element;
			that.taskMap[appId] = taskItem;
			that.taskLength++;
			var item_w = taskItem.element.css("width").replace("px", "");
			that.calculateWidth(item_w);
			setTimeout(proxy(that.resizeHandler, that), 10);
		},
		
		/**
		 * 计算任务栏宽度.
		 * @param itemWidth
		 */
		calculateWidth: function(itemWidth){
			var _inner_w = itemWidth * this.taskLength;
			inner.css("width", _inner_w+"px");
			element.css("width", _inner_w+"px");
		},
		
		/**
		 * 删除任务项并自动打开下一个窗口.
		 * @param appId taskMap中的appId.
		 */
		removeTaskItem: function (appId){
			var that = this;
			var taskItem = that.taskMap[appId];
			delete that.taskMap[appId];
			that.taskLength--;
			if(that.currentTaskItem && that.currentTaskItem.app.id==appId)
				that.currentTaskItem = null;
			var item_w = taskItem.element.css("width").replace("px", "");
			that.calculateWidth(item_w);
			that.resizeHandler();
			that.reSetCurrentTaskItem();
		},
		
		/**
		 * 如果taskMap中还有任务项,调用最右边的任务项的taskClick()函数..
		 */
		reSetCurrentTaskItem:function (){
			var that = this, taskItem = null, time = 0;
			for(var key in that.taskMap){
				var task = that.taskMap[key];
				if(task.window.createTime>time){
					time = task.window.createTime;
					taskItem = task;
				}
			}
			if(!!taskItem){
				taskItem.taskClick();
				//that.setCurrentTaskItem(appId);
			}
		},
		
		/**
		 * 设置当前选择的任务项
		 * @param appId 应用ID
		 */
		setCurrentTaskItem: function (appId){
			var that = this
			currentTaskItem = that.currentTaskItem;
			if(currentTaskItem)
				currentTaskItem.element.removeClass(TASKCURRENT);
			currentTaskItem = that.currentTaskItem = that.taskMap[appId];
			if(currentTaskItem)
				currentTaskItem.element.addClass(TASKCURRENT);
		},
		
		/**
		 * 浏览器大小改变时调整的函数.
		 * 根据任务栏的大小设置左右箭头(按钮)显示或隐藏.
		 */
		resizeHandler: function(){
			var that = this,
				element = that.element,
				_inner = that.taskContainerInner,
				taskPreBox = that.taskPreBox,
				taskNextBox = that.taskNextBox,
				win = $(window),
				winWidth = win.width(),
				taskItems = that.element.find(_P + TASKITEMBOX),
				taskItemWidth = taskItems.width(),
				maxContainerWidth = _inner.width(),
				containerWidth = element.width();
			
			//var console = $("#console").append("<p>winWidth:"+winWidth+", maxContainerWidth:"+maxContainerWidth+", _width:"+_width+"  </p>");
			//console.scrollTop(console.get(0).scrollHeight);
			
			if(winWidth >= maxContainerWidth){
				element.css("width", maxContainerWidth+"px");
				taskPreBox.add(taskNextBox).css("display", "none");
			}else if(winWidth < maxContainerWidth){
				var _width = winWidth - taskPreBox.width() - taskNextBox.width();
				element.css("width", (_width - 6)+"px");
				taskPreBox.add(taskNextBox).css("display", "block");
			}
			that.centerSelectedTaskItem();
		},
		
		/**
		 * 当浏览器窗口的大小改变时,将当前选中的任务项显示出来.
		 */
		centerSelectedTaskItem: function (){
			var that = this,
			element = that.element,
			_inner = that.taskContainerInner,
			currentTaskItem = element.find(_P+TASKCURRENT),
			maxContainerWidth = _inner.width(),
			containerWidth = element.width();
			if(currentTaskItem.length==1){
				var _right = -1 * currentTaskItem.width() * currentTaskItem.nextAll().length;
				var marginRight = containerWidth<maxContainerWidth ? 
									(maxContainerWidth + _right) < containerWidth ? containerWidth - maxContainerWidth : _right : 0;
				if(that.taskNextBox.css("display")=="block"){
					that.taskNextBox.find("a").removeClass(TASKNEXTDISABLE);
					that.taskPreBox.find("a").removeClass(TASKPREVIOUSDISABLE);
					if(marginRight==0){
						that.taskNextBox.find("a").addClass(TASKNEXTDISABLE);
					}else if((maxContainerWidth + marginRight) == containerWidth){
						that.taskPreBox.find("a").addClass(TASKPREVIOUSDISABLE);
					}
				}
			_inner.css("marginRight", marginRight)
			}
		}
	});
	kendo.ui.plugin(TaskContainer);
	
	/**
	 * 任务栏任务项组件
	 */
	var TaskItem = Widget.extend({
		
		/**
		 * 
		 * @param element 
		 * @param options {container: 任务栏组件, window: 应用窗口组件}
		 */
		init: function (element, options){
			var that = this;
			Widget.fn.init.call(that, element, options);
			that._createTaskItem();
		}, 
		
		options: {
            name: "TaskItem"
        },
        
        _createTaskItem: function (){
        	var that = this, options = that.options,
        	container = options.container,
        	window = options.window,
			contentHtml = that.element;
        	
        	if(container && window){
        		var app = window.options.app;
            	contentHtml.addClass(TASKGROUP)
            	.append("<div class=\""+TASKITEMBOX+"\" style=\"display: block;\">"
            		  + "<a id=\"taskItem_"+app.id+"\" class=\"task-item fist-task-item\" href=\"#\" appid=\""+app.id+"\" title=\""+app.name+"\">"
            		  + "<div class=\""+TASKITEMICON+"\">"
            		  + "<img src=\"framework/images/icon/"+app.id+"-mid.png\">"
            		  + "</div><div class=\""+TASKITEMTXT+"\">"+app.name+"</div>"
            		  + "</a></div></div>");
            	container.addTaskItem(that, app.id);
            	container.setCurrentTaskItem(app.id);
            	window.taskItem = that;
            	that.app = window.app;
            	that.container = container;
            	that.window = window;
        	}
        }, 
        
        /**
         * 单击事件
         */
        taskClick: function(){
        	var that = this,display = that.window.wrapper.css("display");
			if(display=="none"){
				that.window.open();
    			that.container.centerSelectedTaskItem()
    		}
			if(that.currentTaskItem == that){
        		if(display!="none") {
        			that.window.minimize();
        		}
			}else {
				if(display!="none") {
					that.window.toFront();
        		}
				that.container.setCurrentTaskItem(that.app.id);
				that.container.centerSelectedTaskItem()
			}
        },
        
        destroy: function (){
        	var that = this;
        	that.container.removeTaskItem(that.app.id);
        	Widget.fn.destroy.call(that);
            kendo.destroy(that.element);
            that.element.remove();
        }
	});
	kendo.ui.plugin(TaskItem);
})(window.kendo.jQuery);