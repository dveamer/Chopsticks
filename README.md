# Chopsticks

�������� "¦�� �����"�� �ǹ̷� ���� �̸�����  
�� ���� ������ ����Ʈ/����/DB ���� ���ϱ� ���� ���α׷��Դϴ�.  


�������, ���θ��� ��ϰ� �ְ� Paypal�� ���ؼ� ��ǰ�� �Ȱ� �ִٰ� �����غ��ô�.  
���θ����� �߻��� ���������� DB�� �����߽��ϴ�.  
�� �޸��� Paypal������ �����Ḧ ���� �����ݾ��� �۱����ݴϴ�.  
Paypal������ �۱��ϱ� ���� ��ü�����ݾ�, ������, �۱ݿ����� ���� ���� ������ ������ ���̸�,  
��û�� Paypal���� ������ �������� ������ ������ ���Դϴ�.  
DB�� �����ص� �츮�� ����� ���������� Paypal�� ������ �������� ������ ���� ���غ� �ʿ䰡 �ֽ��ϴ�.  

�̷��� ���ó�� �ΰ��� ����Ʈ�� ���ؾ��Ѵٸ� Chopsticks�� �����ϰ� ����� �� �ֽ��ϴ�.  
 
## Sample Result

Json������ ������ �־����� ��ü�鵵 List, Map ���·� �����˴ϴ�.  

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

 * result : ��ü�� �����ϸ� true
 * error, errorMsg : ó������ ������ �߻��� ��� ��µ�
 * totalCntOfLefts, totalCntOfRights : ��ü �� 
 * normalCnt : ���� �� ��
 * cntOfOnlyLefts : ���ʸ� �����ϴ� �� ��
 * cntOfOnlyRights : �����ʸ� �����ϴ� �� ��
 * duplicatedCntOfLefts, duplicatedCntOfRights : �ߺ� �� �� 
   - 5���� �����Ͱ� �� 2���� �����Ѵٸ� 10������ ���
   - 5���� �����Ͱ� �� 3���� �����Ѵٸ� 15������ ���
 * crashCntOfLefts, crashCntOfRights : key�� ������ ���� �ٸ� �� �� 

## Requirement
 * Java 8.0 �̻�


