# Chopsticks

젓가락의 "짝을 맞춘다"는 의미로 지은 이름으로  
두 개의 데이터 리스트/파일/DB 등을 비교하기 위한 프로그램입니다.  


예를들어, 쇼핑몰을 운영하고 있고 Paypal을 통해서 상품을 팔고 있다고 생각해봅시다.  
쇼핑몰에서 발생한 결제내역을 DB에 보관했습니다.  
한 달마다 Paypal에서는 수수료를 떼고 결제금액을 송금해줍니다.  
Paypal에서는 송금하기 전에 전체결제금액, 수수료, 송금예정액 등을 담은 보고서를 제공할 것이며,  
요청시 Paypal에서 취합한 결제내역 파일을 보내줄 것입니다.  
DB에 보관해둔 우리가 기록한 결제내역과 Paypal이 보내온 결제내역 파일을 서로 비교해볼 필요가 있습니다.  

이러한 경우처럼 두개의 리스트를 비교해야한다면 Chopsticks을 유용하게 사용할 수 있습니다.  
 
## Sample Result

Json형태의 보고서가 주어지며 객체들도 List, Map 형태로 제공됩니다.  

~~~json
    {  result : false
     , error : false
     , errorMsg : 
     , totalCntOfLefts : 1900000
     , totalCntOfRights : 1700000
     , normalCnt : 1200000
     , cntOfOnlyLefts : 300000
     , cntOfOnlyRights : 200000
     , duplicatedCntOfLefts : 200000
     , duplicatedCntOfRights : 200000
     , crashCntOfLefts : 100000
     , crashCntOfRights : 100000
    }
~~~

 * result : 전체가 동일하면 true
 * error, errorMsg : 처리도중 에러가 발생할 경우 출력됨
 * totalCntOfLefts, totalCntOfRights : 전체 건 
 * normalCnt : 동일 건 수
 * cntOfOnlyLefts : 왼쪽만 존재하는 건 수
 * cntOfOnlyRights : 오른쪽만 존재하는 건 수
 * duplicatedCntOfLefts, duplicatedCntOfRights : 중복 건 수 
   - 5건의 데이터가 각 2개씩 존재한다면 10건으로 출력
   - 5건의 데이터가 각 3개씩 존재한다면 15건으로 출력
 * crashCntOfLefts, crashCntOfRights : key는 같지만 값이 다른 건 수 

## Requirement
 * Java 8.0 이상


