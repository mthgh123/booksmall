//KindEditor变量
var editor;

$(function () {

    //详情编辑器
    editor = KindEditor.create('textarea[id="editor"]', {
        items: ['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
            'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
            'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
            'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
            'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
            'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'multiimage',
            'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
            'anchor', 'link', 'unlink'],
        uploadJson: '/admin/upload/file',
        filePostName: 'file'
    });

    new AjaxUpload('#uploadGoodsCoverImg', {
        action: '/admin/upload/file',
        name: 'file',
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif格式的文件！');
                return false;
            }
        },
        onComplete: function (file, r) {
            if (r != null && r.resultCode == 200) {
                $("#goodsCoverImg").attr("src", r.data);
                $("#goodsCoverImg").attr("style", "width: 128px;height: 128px;display:block;");
                return false;
            } else {
                alert("error");
            }
        }
    });
});

$('#confirmButton').click(function () {
    var goodsName = $('#goodsName').val();
    var originalPrice = $('#originalPrice').val();
    var sellingPrice = $('#sellingPrice').val();
    var stockNum = $('#stockNum').val();
    var author = $('#author').val();
    var goodsCategoryId = $('#levelTwo option:selected').val();
    var goodsSellStatus = $("input[name='goodsSellStatus']:checked").val();
    var goodsDetailContent = editor.html();
    if (isNull(goodsCategoryId)) {
        swal("请选择分类", {
            icon: "error",
        });
        return;
    }
    if (isNull(goodsName)) {
        swal("请输入书籍名称", {
            icon: "error",
        });
        return;
    }
    if (!validLength(goodsName, 100)) {
        swal("书籍名称过长", {
            icon: "error",
        });
        return;
    }
    if (isNull(author)) {
        swal("请输入作者姓名", {
            icon: "error",
        });
        return;
    }
    if (!validLength(tag, 100)) {
        swal("标签过长", {
            icon: "error",
        });
        return;
    }
    if (!validLength(goodsIntro, 100)) {
        swal("简介过长", {
            icon: "error",
        });
        return;
    }
    if (isNull(originalPrice) || originalPrice < 1) {
        swal("请输入书籍价格", {
            icon: "error",
        });
        return;
    }
    if (isNull(sellingPrice) || sellingPrice < 1) {
        swal("请输入书籍售卖价", {
            icon: "error",
        });
        return;
    }
    if (isNull(stockNum) || sellingPrice < 0) {
        swal("请输入书籍库存数", {
            icon: "error",
        });
        return;
    }
    if (isNull(goodsSellStatus)) {
        swal("请选择上架状态", {
            icon: "error",
        });
        return;
    }
    if (isNull(goodsDetailContent)) {
        swal("请输入书籍介绍", {
            icon: "error",
        });
        return;
    }
    if (!validLength(goodsDetailContent, 50000)) {
        swal("商品介绍内容过长", {
            icon: "error",
        });
        return;
    }
    $('#goodsModal').modal('show');
});

$('#saveButton').click(function () {
    var goodsId = $('#goodsId').val();
    var goodsCategoryId = $('#levelTwo option:selected').val();
    var goodsName = $('#goodsName').val();
    var originalPrice = $('#originalPrice').val();
    var sellingPrice = $('#sellingPrice').val();
    var author = $('#author').val();
    var stockNum = $('#stockNum').val();
    var goodsSellStatus = $("input[name='goodsSellStatus']:checked").val();
    var goodsDetailContent = editor.html();
    var goodsCoverImg = $('#goodsCoverImg')[0].src;
    if (isNull(goodsCoverImg) || goodsCoverImg.indexOf('img-upload') != -1) {
        swal("封面图片不能为空", {
            icon: "error",
        });
        return;
    }
    var url = '/admin/goods/save';
    var swlMessage = '保存成功';
    var data = {
        "goodsName": goodsName,
        "author": author,
        "goodsCategoryId": goodsCategoryId,
        "originalPrice": originalPrice,
        "sellingPrice": sellingPrice,
        "stockNum": stockNum,
        "goodsDetailContent": goodsDetailContent,
        "goodsCoverImg": goodsCoverImg,
        "goodsCarousel": goodsCoverImg,
        "goodsSellStatus": goodsSellStatus
    };
    /*新增书籍的时候，goodsId=0，而修改已经存在的书籍信息的时候，此时的goodsId>0*/
    if (goodsId > 0) {
        url = '/admin/goods/update';
        swlMessage = '修改成功';
        data = {
            "goodsId": goodsId,
            "goodsName": goodsName,
            "author": author,
            "goodsCategoryId": goodsCategoryId,
            "originalPrice": originalPrice,
            "sellingPrice": sellingPrice,
            "stockNum": stockNum,
            "goodsDetailContent": goodsDetailContent,
            "goodsCoverImg": goodsCoverImg,
            "goodsCarousel": goodsCoverImg,
            "goodsSellStatus": goodsSellStatus
        };
    }
    console.log(data);
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
            if (result.resultCode == 200) {
                //隐藏模态框，并紧接着显示sweetAlert弹窗
                $('#goodsModal').modal('hide');
                swal({
                    title: swlMessage,
                    type: 'success',
                    showCancelButton: false,
                    confirmButtonColor: '#1baeae',
                    confirmButtonText: '返回商品列表',
                    confirmButtonClass: 'btn btn-success',
                    buttonsStyling: false
                }).then(function () {
                    window.location.href = "/admin/goods";
                })
            } else {
                $('#goodsModal').modal('hide');
                swal(result.message, {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });
});

$('#cancelButton').click(function () {
    window.location.href = "/admin/goods";
});

//下方代码对应两级联动的选择框
$('#levelOne').on('change', function () {
    $.ajax({
        url: '/admin/categories/listForSelect?categoryId=' + $(this).val(),
        type: 'GET',
        success: function (result) {
            if (result.resultCode == 200) {
                var levelTwoSelect = '';
                var secondLevelCategories = result.data.secondLevelCategories;
                var length2 = secondLevelCategories.length;
                for (var i = 0; i < length2; i++) {
                    levelTwoSelect += '<option value=\"' + secondLevelCategories[i].categoryId + '\">' + secondLevelCategories[i].categoryName + '</option>';
                }
                $('#levelTwo').html(levelTwoSelect);
            } else {
                swal(result.message, {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });
});

