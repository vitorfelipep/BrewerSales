var Brewer  = Brewer || {};

Brewer.UploadFoto = (function() {
	
	//Declaro a função construtora
	function UploadFoto() {
		
		this.inputNomeFoto = $('input[name=foto]'); 
		this.inputContentType = $('input[name=contentType]');
		this.htmlFotoCervejaTemplate = $('#foto-cerveja').html();
		this.template = Handlebars.compile(this.htmlFotoCervejaTemplate);
		this.containerFotoCerveja = $('.js-container-foto-cerveja');
		this.uploadDrop = $('#upload-drop'); 
	}
	
	UploadFoto.prototype.iniciar = function () {
		var settings = {
				type: 'json',
				filelimit: 1,
				allow: '*.(jpg|jpeg|png)',
				action: '/brewer/fotos',
				complete: function(resposta) {
					
				}
		}
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
	}
	
	return UploadFoto;
	
})();


//Instancia o objeto e acessa a function iniciar.
$(function () {
	var uploadFoto = new Brewer.UploadFoto();
	uploadFoto.iniciar();
});