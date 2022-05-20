<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="../../include/head.jsp" %>
<body class="hold-transition skin-blue sidebar-mini layout-boxed">
<div class="wrapper">

    <!-- Main Header -->
    <%@ include file="../../include/main_header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="../../include/left_column.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Page Header
                <small>Optional description</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
                <li class="active">Here</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">
            <div class="col-lg-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">글제목 : ${article.title}</h3>
                    </div>
                </div>
                <div class="box-body" style="height: 700px">
                    ${article.content}
                </div>
                <div class="box-footer">
                    <div class="user-block">
                        <img class="img-circle img-bordered-sm" src="/dist/img/user1-128x128.jpg" alt="user image">
                        <span class="username">
                            <a href="#">${article.writer}</a>
                        </span>
                        <span class="description"><fmt:formatDate value="${article.regDate}" pattern="yyyy-MM-dd a HH:mm" /></span>
                    </div>
                </div>
                <div class="box-footer">
                    <form role="form" method="post">
                        <input type="hidden" name="articleNo" value="${article.articleNo}">
                        <%--현재페이지 필드 추가--%>
                        <input type="hidden" name="page" value="${criteria.page}">
                        <input type="hidden" name="perPageNum" value="${criteria.perPageNum}">
                    </form>
                    <button type="submit" class="btn btn-primary listBtn"><i class="fa fa-list"></i>목록</button>
                    <div class="pull-right">
                        <button type="submit" class="btn btn-warning modBtn"><i class="fa fa-edit"></i>수정</button>
                        <button type="submit" class="btn btn-danger delBtn"><i class="fa fa-trash"></i>삭제</button>
                    </div>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <%@ include file="../../include/main_footer.jsp" %>

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
            <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
            <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
            <!-- Home tab content -->
            <div class="tab-pane active" id="control-sidebar-home-tab">
                <h3 class="control-sidebar-heading">Recent Activity</h3>
                <ul class="control-sidebar-menu">
                    <li>
                        <a href="javascript:;">
                            <i class="menu-icon fa fa-birthday-cake bg-red"></i>

                            <div class="menu-info">
                                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                                <p>Will be 23 on April 24th</p>
                            </div>
                        </a>
                    </li>
                </ul>
                <!-- /.control-sidebar-menu -->

                <h3 class="control-sidebar-heading">Tasks Progress</h3>
                <ul class="control-sidebar-menu">
                    <li>
                        <a href="javascript:;">
                            <h4 class="control-sidebar-subheading">
                                Custom Template Design
                                <span class="pull-right-container">
                    <span class="label label-danger pull-right">70%</span>
                  </span>
                            </h4>

                            <div class="progress progress-xxs">
                                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                            </div>
                        </a>
                    </li>
                </ul>
                <!-- /.control-sidebar-menu -->

            </div>
            <!-- /.tab-pane -->
            <!-- Stats tab content -->
            <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
            <!-- /.tab-pane -->
            <!-- Settings tab content -->
            <div class="tab-pane" id="control-sidebar-settings-tab">
                <form method="post">
                    <h3 class="control-sidebar-heading">General Settings</h3>

                    <div class="form-group">
                        <label class="control-sidebar-subheading">
                            Report panel usage
                            <input type="checkbox" class="pull-right" checked>
                        </label>

                        <p>
                            Some information about this general settings option
                        </p>
                    </div>
                    <!-- /.form-group -->
                </form>
            </div>
            <!-- /.tab-pane -->
        </div>
    </aside>
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
    immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<%@ include file="../../include/plugin_js.jsp" %>
<script>
    $(document).ready(function(){
        var formObj = $('form[role="form"]');
        console.log(formObj);

        /*
        * 수정버튼 이벤트
        */
        $('.modBtn').on('click', function(){
            formObj.attr('action', '/article/paging/modify');
            formObj.attr('method', 'get');
            formObj.submit();
        });

        /*
        * 삭제버튼 이벤트
        */
        $('.delBtn').on('click', function(){
            formObj.attr('action', '/article/paging/remove');
            formObj.submit();
        });

        /*
         * 목록버튼 이벤트
         */
        $('.listBtn').on('click', function(){
            self.location = '/article/paging/list';
        });
    });
</script>
</body>
</html>