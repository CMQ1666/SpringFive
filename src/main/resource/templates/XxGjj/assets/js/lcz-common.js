var Lcz_common = {
    checkAll: function () {
        
        $('table th input:checkbox').on('click', function () {
            var that = this;
            
            $(this).closest('table').find('tr > td:first-child input:checkbox')
					.each(function () {
					    this.checked = that.checked;
					    $(this).closest('tr').toggleClass('selected');
					});

        });
    },
    checkNameAll: function (checkAllId, oncheckName) {
        $('#' + checkAllId).on('click', function () {
            if (this.checked) {
                $('input[name=' + oncheckName + ']').each(function () {
                    this.checked = true;
                });
            } else $('input[name=' + oncheckName + ']').removeAttr('checked');
        });
    },
    getCheckValue: function (checkName, formName) {
        var id_array = new Array();
        $('input[name=' + checkName + ']:checked').each(function () {
            id_array.push($(this).val()); //向数组中添加元素  
        });
        var idstr = id_array.join(','); //将数组元素连接起来以构建一个字符串  
        return idstr;
    }
}  