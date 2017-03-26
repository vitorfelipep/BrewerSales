Brewer = Brewer || {};

Brewer.MultiSelecao = (function() {
	
	function MultiSelecao() {
		this.statusBtn = $('.js-status-btn');
		this.selecaoCheckbox = $('.js-selecao');
		this.selecaoTodosCheckBox = $('.js-selecao-todos');
	}
	
	MultiSelecao.prototype.iniciar = function() {
		this.statusBtn.on('click', onStatusBtnClicado.bind(this));
		this.selecaoTodosCheckBox.on('click', onSelecaoTodosClicados.bind(this));
		this.selecaoCheckbox.on('click', onSelecaoClicado.bind(this));
	}
	
	function onSelecaoTodosClicados() {
		var status = this.selecaoTodosCheckBox.prop('checked');
		console.log(status);
		this.selecaoCheckbox.prop('checked', status);
		statusBotaoAcao.call(this, status);
	}
	
	function onStatusBtnClicado(event) {
		var botaoClicado = $(event.currentTarget);
		var status = botaoClicado.data('status');
		var url = botaoClicado.data('url');
		
		var checkBoxSelecionados = this.selecaoCheckbox.filter(':checked');
		var codigos = $.map(checkBoxSelecionados, function(c) {
			return $(c).data('codigo');
		});
		
		if (codigos.length > 0) {
			$.ajax({
				url: url,
				method: 'PUT',
				data: {
					codigos: codigos,
					status: status
				}, 
				success: function() {
					window.location.reload();
				}
			});
			
		}
	}
	
	function onSelecaoClicado() {
		var selecaoCheckBoxChecados = this.selecaoCheckbox.filter(':checked');
		console.log('selecaoCheckBoxChecados', selecaoCheckBoxChecados.length);
		this.selecaoTodosCheckBox.prop('checked', selecaoCheckBoxChecados.length >= this.selecaoCheckbox.length);
		statusBotaoAcao.call(this, selecaoCheckBoxChecados.length);
	}
	
	function statusBotaoAcao(ativar) {
		ativar ? this.statusBtn.removeClass('disabled') : this.statusBtn.addClass('disabled');
	}
	
	return MultiSelecao;
	
}());

$(function() {
	var multiSelecao = new Brewer.MultiSelecao();
	multiSelecao.iniciar();
});