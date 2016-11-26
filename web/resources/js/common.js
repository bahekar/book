/**
 * 
 * Author : Pratik
 *
 */
$(document).ready(function(){
    $('input[type="text"]').click(function(){
       var id = this.id;
       $("#"+id).removeClass("error_input");
       $("#"+id+"_err").hide();
    });
    $('textarea').click(function(){
       var id = this.id;
       $("#"+id).removeClass("error_input");
       $("#"+id+"_err").hide();
    });
    $('select').click(function(){
       var id = this.id;
       $("#"+id).removeClass("error_input");
       $("#"+id+"_err").hide();
    });
});
function addclass(id){
    $("#"+id).addClass("error_input");
    $("#"+id+"_err").show();
}
function removeclass(id){
    $("#"+id).removeClass("error_input");
    $("#"+id+"_err").hide();
}
///////////////// For numbers only /////////////////////////
function numbersonly(e){
    var unicode=e.charCode? e.charCode : e.keyCode;
    //alert(unicode);
    if(unicode==43)
        return true;
    if (unicode!=8) {
        if (unicode<46||unicode>57)
            if(unicode != 9){
                return false;
            }
    }
};