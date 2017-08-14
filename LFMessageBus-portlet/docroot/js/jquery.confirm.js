(function($){
	
	$.confirm = function(params){
		
		if($('#confirmOver').length){
			// A confirm is already shown on the page:
			return false;
		}
		
		var buttonHTML = '';
		$.each(params.buttons,function(name,obj){
			
			// Generating the markup for the buttons:
			
			buttonHTML += '<a href="#" class="btn btn-outline button '+obj['class']+'">'+name+'<span></span></a>';
			
			if(!obj.action){
				obj.action = function(){};
			}
		});
		
		var markup = [
			/*Custome dialog*/
			'<div class="modal modal-warning" id="confirmOver" role="dialog" style="display: block; padding-left: 17px;">',
				'<div class="modal-dialog">',
					'<div class="modal-content">',
						'<div class="modal-header">',
							'<h4 class="modal-title">',params.title,'</h4>',
						'</div>	',
						'<div class="modal-body">',
							'<p>',params.message,'</p>',
						'</div>',
						'<div class="modal-footer">',
							buttonHTML,
						'</div>',
					'</div>',
				'</div>',
			'</div>'
				
		].join('');
		
		$(markup).hide().appendTo('body').fadeIn(1000);
		
		var buttons = $('.modal-content .button'),
			i = 0;

		$.each(params.buttons,function(name,obj){
			buttons.eq(i++).click(function(){
				
				// Calling the action attribute when a
				// click occurs, and hiding the confirm.
				
				obj.action();
				$.confirm.hide();
				return false;
			});
		});
	}

	$.confirm.hide = function(){
		$('#confirmOver').fadeOut(function(){
			$(this).remove();
		});
	}
	
})(jQuery);