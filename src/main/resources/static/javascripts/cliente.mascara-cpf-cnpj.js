var Brewer = Brewer || {};

Brewer.MaskaraCpfCnpj = (function() {
	
	function MascaraCpfCnpj() {
		this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
		this.labelCpfCnpj = $('[for=cpfOuCnpj]');
		this.inputCpfCnpj = $('#cpfOuCnpj');
	}
	
	MascaraCpfCnpj.prototype.iniciar = function() {
		this.radioTipoPessoa.on('change', onTipoPessoaAlterado.bind(this));
		var tipoPessoaSelecionada = this.radioTipoPessoa.filter(':checked')[0];
		if(tipoPessoaSelecionada) {
			aplicarMaskara.call(this, $(tipoPessoaSelecionada));
		}
	};
	
	function onTipoPessoaAlterado(event) {
		var tipoPessoaSelecionada = $(event.currentTarget);
		aplicarMaskara.call(this, tipoPessoaSelecionada);
		this.inputCpfCnpj.val('');
	};
	
	function aplicarMaskara(tipoPessoaSelecionada) {
		this.labelCpfCnpj.text(tipoPessoaSelecionada.data('documento'));
		this.inputCpfCnpj.mask(tipoPessoaSelecionada.data('mascara'));
		this.inputCpfCnpj.removeAttr('disabled');
		
	}
	
	return MascaraCpfCnpj;
}());

$(function() {
	var mascaraCpfCnpj = new Brewer.MaskaraCpfCnpj();
	mascaraCpfCnpj.iniciar();
})