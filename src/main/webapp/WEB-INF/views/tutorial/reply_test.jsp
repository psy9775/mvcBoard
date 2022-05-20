<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="../include/head.jsp" %>
<body class="hold-transition skin-blue sidebar-mini layout-boxed">
<div class="wrapper">

	<!-- Main Header -->
	<%@ include file="../include/main_header.jsp" %>
	<!-- Left side column. contains the logo and sidebar -->
	<%@ include file="../include/left_column.jsp" %>

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
						<h3 class="box-title">댓글 작성</h3>
					</div>
				</div>
				<div class="box-body">
					<div class="form-group">
						<label for="newReplyText">댓글 내용</label>
						<input class="form-control" id="newReplyText" name="replyText" placeholder="댓글 내용을 입력해주세요">
					</div>
					<div class="form-group">
						<label for="newReplyWriter">댓글 작성자</label>
						<input class="form-control" id="newReplyWriter" name="replyWriter" placeholder="댓글 작성자를 입력해주세요">
					</div>
					<div class="box-footer">
						<button type="button" class="btn btn-success pull-left" id="replyAddBtn">등록</button>
					</div>
				</div>

				<div class="box-footer">
					<ul id="replies">

					</ul>
				</div>
				<div class="box-footer">
					<div class="text-center">
						<ul class="pagination pagination-sm no-margin">

						</ul>
					</div>
				</div>
			</div>

			<div class="modal fade" id="modifyModal" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">댓글 수정창</h4>
						</div>
						<div class="modal-body">
							<div class="from-group">
								<label for="replyNo">댓글 번호</label>
								<input class="from-control" id="replyNo" name="replyNo" readonly>
							</div>
							<div class="from-group">
								<label for="replyText">댓글 내용</label>
								<input class="form-control" id="replyText" name="replyText" placeholder="댓글 내용을 입력해주세요">
							</div>
							<div class="form-group">
								<label for="replyWriter">댓글 작성자</label>
								<input class="form-control" id="replyWriter" name="replyWriter" readonly>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
							<button type="button" class="btn btn-success modalModBtn">수정</button>
							<button type="button" class="btn btn-danger modalDelBtn">삭제</button>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->

	<!-- Main Footer -->
	<%@ include file="../include/main_footer.jsp" %>

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
<%@ include file="../include/plugin_js.jsp" %>
<script>
	var articleNo = 1009;

	//목록페이지 번호 변수 선언, 1로 초기화(첫번째 페이지)
	var replyPageNum = 1;

	//댓글 목록 호출
	//getReplies();
	getRepliesPaging(replyPageNum);

	//댓글 목록 출력 함수(사용안함)
	//printPageNumbers()함수로 대체
	function getReplies(){
		$.getJSON("/replies/all/" + articleNo, function(data){
			console.log(data);

			var str = '';
			$(data).each(function(){
				str += '<li data-replyNo="' + this.replyNo + '" class="replyLi">'
					+ '<p class="replyText">' + this.replyText + '</p>'
					+ '<p class="replyWriter">' + this.replyWriter + '</p>'
					+ '<button type="button" class="btn btn-sx btn-success" data-toggle="modal" data-target="#modifyModal">댓글 수정</button>'
					+ '</li>'
					+ '<hr/>';
			});

			$('#replies').html(str);
		});
	}

	$('#replyAddBtn').on('click', function(){

		var replyText = $('#newReplyText');
		var replyWriter = $('#newReplyWriter');

		var replyTextVal = replyText.val();
		var replyWriterVal = replyWriter.val();

		$.ajax({
			type : 'post',
			url : '/replies',
			headers : {
				'Content-type' : 'application/json',
				'X-HTTP-Method-Override' : 'POST'
			},
			dataType : 'text',
			data : JSON.stringify({
				articleNo : articleNo,
				replyText : replyTextVal,
				replyWriter : replyWriterVal
			}),
			success : function (result) {
				if(result == 'regSuccess') {
					alert('댓글 등록 완료!');
				}
				getReplies();
				replyText.val('');
				replyWriter.val('');
			}
		})
	});

	/*수정 모달창 내용 입력*/
	$('#replies').on('click', '.replyLi button', function(){
		var reply = $(this).parent();
		var replyNo = reply.attr('data-replyNo');
		var replyText = reply.find('.replyText').text();
		var replyWriter = reply.find('.replyWriter').text();
		$('#replyNo').val(replyNo);
		$('#replyText').val(replyText);
		$('#replyWriter').val(replyWriter);
	});

	/*댓글 삭제 이벤트*/
	$('.modalDelBtn').on('click', function(){
		var replyNo = $(this).parent().parent().find('#replyNo').val();
		$.ajax({
			type : 'delete',
			url : '/replies/' + replyNo,
			headers : {
				'Content-type' : 'application/json',
				'X-HTTP-Method-Override' : 'DELETE'
			},
			dataType : 'text',
			success : function(result){
				console.log('result : ' + result);
				if(result == 'delSuccess'){
					alert('댓글 삭제 완료!');
					$('#modifyModal').modal('hide');
					getReplies();
				}
			}
		})
	});

	/*댓글 수정 이벤트*/
	$('.modalModBtn').on('click', function(){
		var reply = $(this).parent().parent();
		console.log(reply);
		var replyNo = reply.find('#replyNo').val();
		var replyText = reply.find('#replyText').val();

		$.ajax({
			type : 'put',
			url : '/replies/' + replyNo,
			headers : {
				'Content-type' : 'application/json',
				'X-HTTP-Method-Override' : 'PUT'
			},
			data : JSON.stringify({
				replyText: replyText
			}),
			success : function(result) {
				console.log('result : ' + result);
				if(result == 'modSuccess') {
					alert('댓글 수정 완료!');
					$('#modifyModal').modal('hide');
					getReplies();
				}
			}
		})
	});

	/*댓글 목록 조회(페이징처리)*/
	function getRepliesPaging(page){
		$.getJSON('/replies/' + articleNo + '/' + page, function(data){
			console.log(data);
			var str = '';
			$(data.replies).each(function(){
				console.log(this);
				str += '<li data-replyNo="' + this.replyNo + '" class="replyLi">'
					+ '<p class="replyText">' + this.replyText + '</p>'
					+ '<p class="replyWriter">' + this.replyWriter + '</p>'
					+ '<button type="button" class="btn btn-sx btn-success" data-toggle="modal" data-target="#modifyModal">댓글 수정</button>'
					+ '</li>'
					+ '<hr/>';
			});
			$('#replies').html(str);
			//페이지 번호 출력 호출
			printPageNumbers(data.pageMaker);
		});
	}

	/*댓글 목록 페이지 번호 출력 함수*/
	function printPageNumbers(pageMaker) {
		var str = '';
		//이전 버튼 활성화
		if (pageMaker.prev) {
			str += '<li><a href="' + (pageMaker.startPage - 1) + '">이전</a></li>';
		}
		//페이지 번호 생성
		for (var i = pageMaker.startPage, len = pageMaker.endPage ; i <= len; i++) {
			var strCalss = pageMaker.criteria.page == i ? 'class="active"' : '' ;
			str += '<li ' + strCalss + '><a href="' + i + '">' + i + '</a></li>';
		}
		//다음 버튼 활성화
		if(pageMaker.next) {
			str += '<li><a href="' + (pageMaker.endPage + 1) + '"></a></li>';
		}
		$('.pagination-sm').html(str);
	}

	//목록페이지 번호 출력 이벤트
	$('.pagination').on('click', 'li a', function(event){
		event.preventDefault();
		replyPageNum = $(this).attr('href'); //목록번호 추출
		getRepliesPaging(replyPageNum); //목록 페이지 호출
	});

</script>
</body>
</html>