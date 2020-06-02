$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/carousels/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'carouselId', index: 'carouselId', width: 50, key: true, hidden: true},
            {label: '轮播图', name: 'carouselUrl', index: 'carouselUrl', width: 180, formatter: coverImageFormatter},
            {label: '跳转链接', name: 'redirectUrl', index: 'redirectUrl', width: 120},
            {label: '排序值', name: 'carouselRank', index: 'carouselRank', width: 120},
            {label: '添加时间', name: 'createTime', index: 'createTime', width: 120}
        ],
        height: 560,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    function coverImageFormatter(cellvalue) {
        return "<img src='" + cellvalue + "' height=\"130\" width=\"320\" alt='coverImage'/>";
    }

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

    //当id=uploadCarouselImage的button被按下之后就会触发
    new AjaxUpload('#uploadCarouselImage', {
        action: '/admin/upload/file',
        name: 'file',
        autoSubmit: true,
        responseType: "json",
        //当选择好需要上传的文件之后，就会执行onSubmit代码块
        onSubmit: function (file, extension) {
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif格式的文件！');
                return false;
            }
        },
        // r是一个Result类型的对象。当执行完action: '/admin/upload/file'方法之后，会执行onComplete代码块
        //用于在模态框上回显新增的图片
        onComplete: function (file, r) {
            if (r != null && r.resultCode == 200) {
                //实际上r.data中存储的是图片的URL
                //给id="carouselImg"的标签设置下方的新的属性，用于回显图片
                $("#carouselImg").attr("src", r.data);
                $("#carouselImg").attr("style", "width: 128px;height: 128px;display:block;");
                return false;
            } else {
                alert("error");
            }
        }
    });
});

/**
 * jqGrid重新加载
 */
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

/*jQuery代码*/
function carouselAdd() {
    /*刷新数据，相当于先给数据设定一个默认值*/
    reset();
    /*选取class="modal-title"的标签，将标签的值设为：'轮播图添加'*/
    $('.modal-title').html('轮播图添加');
    /*选取id="carouselModal"的模态框并显示之，.modal('show')就是用来实现这个功能的*/
    $('#carouselModal').modal('show');
}

//绑定modal上的保存按钮
$('#saveButton').click(function () {
    var redirectUrl = $("#redirectUrl").val();
    var carouselRank = $("#carouselRank").val();
    var carouselUrl = $('#carouselImg')[0].src;
    var data = {
        "carouselUrl": carouselUrl,
        "carouselRank": carouselRank,
        "redirectUrl": redirectUrl
    };
    var url = '/admin/carousels/save';
    var id = getSelectedRowWithoutAlert();
    if (id != null) {
        url = '/admin/carousels/update';
        data = {
            "carouselId": id,
            "carouselUrl": carouselUrl,
            "carouselRank": carouselRank,
            "redirectUrl": redirectUrl
        };
    }
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        contentType: 'application/json',
        data: JSON.stringify(data), //用于将javascript数据转化为json数据
        //操作成功
        success: function (result) {
            if (result.resultCode == 200) {
                //模态框隐藏，进而好显示sweetalert弹窗
                $('#carouselModal').modal('hide');
                swal("保存成功", {
                    icon: "success",
                });
                reload();
            } else {
                $('#carouselModal').modal('hide');
                swal(result.message, {
                    icon: "error",
                });
            }
            ;
        },
        //完全的操作失败，比上方的else语句还严重的一种
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });
});

function carouselEdit() {
    reset();
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    //请求数据
    $.get("/admin/carousels/info/" + id, function (r) {
        if (r.resultCode == 200 && r.data != null) {
            //填充数据至modal，从而显示所选中的表单的之前的信息
            $("#carouselImg").attr("src", r.data.carouselUrl);
            $("#carouselImg").attr("style", "height: 64px;width: 64px;display:block;");
            $("#redirectUrl").val(r.data.redirectUrl);
            $("#carouselRank").val(r.data.carouselRank);
        }
    });
    $('.modal-title').html('轮播图编辑');
    $('#carouselModal').modal('show');
}

function deleteCarousel() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要删除数据吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/carousels/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.resultCode == 200) {
                            swal("删除成功", {
                                icon: "success",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(r.message, {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        }
    )
    ;
}


function reset() {
    $("#redirectUrl").val('##');
    $("#carouselRank").val(0);
    $("#carouselImg").attr("src", '/admin/dist/img/img-upload.png');
    $("#carouselImg").attr("style", "height: 64px;width: 64px;display:block;");
    $('#edit-error-msg').css("display", "none");
}