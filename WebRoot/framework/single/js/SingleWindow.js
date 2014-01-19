(function($){
	var kendo = window.kendo,
	Window = kendo.ui.Window,
	activeElement = kendo._activeElement,
	RESIZE = "resize",
	DESTROY = "destroy",
	KWINDOW = ".k-window",
	KWINDOWCONTENT = ".k-window-content",
	KCONTENTFRAME = "k-content-frame",
	TITLEBAR_BUTTONS = ".k-window-titlebar .k-window-action",
	ZINDEX = "zIndex";
	
	
	/*function windowObject(element, name) {
        var contentElement = element.children(KWINDOWCONTENT);

        return contentElement.data("kendoWindow") || contentElement.data("kendo" + name);
    }
	
	function openedModalWindows(name) {
        return $(KWINDOW).filter(function() {
            var wnd = $(this),
                winObj = windowObject(wnd, name);
            return winObj.options.modal && wnd.is(VISIBLE) && winObj.options.visible;
        }).sort(function(a, b){
            return +$(a).css("zIndex") - +$(b).css("zIndex");
        });
    }*/
	
	/**
	 * 桌面窗口
	 */
	var SingleWindow = Window.extend({

        options: {
            name: "SingleWindow",
            isRestore: false
        },
        events:[DESTROY].concat(Window.fn.events),
        
        init: function(element, options){
        	var that = this;
        	Window.fn.init.call(this, element, options);
        	if(that.dragging)
        		that.dragging._draggable._events.drag = [$.proxy(otherExtend.drag, that.dragging)];
        	if(that.resizing)
        		that.resizing._draggable._events.drag = [$.proxy(otherExtend.resize, that.resizing)];
        },
        
        
        _close: function (){
        	this.destroy();
        },
        
        destroy: function (){
        	var that = this, modalWindows, shouldHideOverlay
        	this.trigger(DESTROY);
        	try{
        		Window.fn.destroy.call(this);
        	}catch (e){
        		
        	}
        	/*modalWindows = openedModalWindows(this.options.name);

            shouldHideOverlay = that.options.modal && !modalWindows.length;

            if (shouldHideOverlay) {
                that._overlay(false).remove();
            } else if (modalWindows.length > 0) {
                windowObject(modalWindows.eq(modalWindows.length - 1), that.options.name)._overlay(true);
            }*/
        },

        restore: function () {
            var that = this, options = that.options;
            options.isRestore = true;
            Window.fn.restore.call(that);
            that.options.resizable = !!that.resizing;
            that.options.draggable = !!that.dragging;
            if(that.options.resizable){
            	var wrapper = this.wrapper;
            	$.each("n e s w se sw ne nw".split(" "), function(index, handler) {
                	wrapper.find(".k-resize-" + handler).css("cursor", handler + "-resize");
                });
            }
        },

        maximize: function(){
        	var that = this, options = that.options;
            options.isRestore = false;
            Window.fn.maximize.call(that);
            that.options.resizable = false;
            that.options.draggable = false;
            var wrapper = this.wrapper;
            $.each("n e s w se sw ne nw".split(" "), function(index, handler) {
            	wrapper.find(".k-resize-" + handler).css("cursor", "auto");
            });
        },

        minimize: function(){
        	var that = this, options = that.options;
            //options.isRestore = false;
            Window.fn._close.call(that, true);
        }, 
        
        _createWindow: function(){
        	var that = this, options = that.options,wrapper;
        	Window.fn._createWindow.call(that);
        	that.element.addClass("single-window-content");
        	wrapper = that.element.closest(KWINDOW);
        	wrapper.addClass("single-window");
        },
        
        toFront: function (e) {
            var that = this,
                wrapper = that.wrapper,
                currentWindow = wrapper[0],
                zIndex = +wrapper.css(ZINDEX),
                originalZIndex = zIndex,
                active = activeElement(),
                winElement = that.element,
                target = e && e.target ? e.target : null;

            $(KWINDOW).each(function(i, element) {
                var windowObject = $(element),
                    zIndexNew = windowObject.css(ZINDEX),
                    contentElement = windowObject.find(KWINDOWCONTENT);

                if (!isNaN(zIndexNew)) {
                    zIndex = Math.max(+zIndexNew, zIndex);
                }

                // Add overlay to windows with iframes and lower z-index to prevent
                // trapping of events when resizing / dragging
                if (element != currentWindow && contentElement.find("> ." + KCONTENTFRAME).length > 0) {
                    contentElement.append(templates.overlay);
                }
            });

            if (zIndex == 10001 || originalZIndex < zIndex) {
                wrapper.css(ZINDEX, zIndex + 2);
            }
            that.element.find("> .k-overlay").remove();

            if (!$(active).is(winElement) &&
                !$(target).is(TITLEBAR_BUTTONS + "," + TITLEBAR_BUTTONS + " .k-icon,:input") &&
                (!winElement.find(active).length || !winElement.find(target).length)) {
            	var browser = kendo.support.browser;
            	if(!browser.msie || browser.version>9)
            		winElement.focus();

                var scrollTop = $(window).scrollTop(),
                    windowTop = parseInt(that.wrapper.position().top, 10);
                if (windowTop > 0 && windowTop - scrollTop < 0) {
                    if (scrollTop > 0) {
                        $(window).scrollTop(windowTop);
                    } else {
                        that.wrapper.css("top", scrollTop);
                    }
                }
            }

            return that;
        }
    });
	kendo.ui.plugin(SingleWindow);
	
	
	templates = {
	        overlay: "<div class='k-overlay' />"
	    };
	
	
	
	/**
	 * 桌面窗口:窗口移动和改变大小
	 */
	var otherExtend = {
			drag: function (e) {
				var wnd = this.owner;
				if(wnd.options.draggable){
					this.drag(e);
				}
			},
			
			resize: function(e){
				var wnd = this.owner;
				if(wnd.options.resizable){
					this.drag(e);
				}
			}
	};
        
})(window.kendo.jQuery)