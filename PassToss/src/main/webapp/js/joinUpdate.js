function Postcode() {
        new daum.Postcode({
            oncomplete: function(data) {
            	console.log(data.zonecode)
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
 
                // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 도로명 조합형 주소 변수
 
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                if(fullRoadAddr !== ''){
                    fullRoadAddr += extraRoadAddr;
                }
 
                
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $('#post1').val(data.zonecode);
                $('#address').val(fullRoadAddr);
     
            }
        }).open();
    }//function Postcode()
    
    
    
    
	$(function(){
		
		let checkemail=true;
			
		$('input[name=email]').on('keyup',function(){
			
			// +는 1회 이상반복을 의미하고 {1,}와 동일합니다
			//\w+ 는 [A-Za-zz0-9_]를 1개이상 사용하라는 의미
			const pattern = /^\w+@\w+[.]\w{3}$/;
			const email_value = $(this).val();
			if(!pattern.test(email_value)){
				
				$("#email_message").css('color','red').html("이메일 형식이 맞지 않습니다");
				checkemail = false;
				
			}else{
				$("#email_message").css('color','green').html("이메일 형식에 맞습니다");
				checkemail = true;
			}
		
		
		})//email 키업
		
		
		$('form').submit(function(){
			
				if(!checkemail){
					alert("email 형식을 확인하세요");
					$("input[name='email']").val('').focus();
					return false;
				
				}
	
	
				
				const post1 = $('#post1');
				const address = $('#address');
				const phone = $('#phone');
				
				var regExp = /\d{2,3}-\d{3,4}-\d{4}/g;
				
				if(!regExp.test(phone.val()))	{
					alert('휴대폰번호에 하이픈을 추가하세요');
					phone.focus();
					return false;
					
			}
				
				if(phone.val().trim()  == ''){
				
					alert('전화번호를 입력하세요');
					phone.focus();
					return false;
				
				}
				
				if(post1.val().trim()  == ''){
				
					alert('우편번호를 입력하세요');
					post1.focus();
					return false;
				
				}
	
				if(!$.isNumeric(post1.val())){
					alert('우편번호는 숫자만 입력 가능합니다.');
					post1.val('').focus();
					return false;
				
				}
	
	
				if(address.val().trim()  == ''){
					
					alert('주소를 입력하세요');
					address.focus();
					return false;
				
				}
				
		
		
		})
		
		
		
		
		
	})