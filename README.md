# java-convenience-store-precourse

### 1. 상품 구매
   - 사용자가 구매하고자 하는 상품의 가격과 수량을 입력받습니다.
   - 총 구매액은 상품별 가격과 수량을 곱한 값으로 계산합니다.
   - 상품을 추가로 구매할 경우 총 구매액이 계속해서 업데이트됩니다.
   - 사용자가 잘못된 값을 입력할 경우 IllegalArgumentException을 발생시켜, "[ERROR]"로 시작하는 에러 메시지와 함께 올바른 값을 입력받습니다.
<br>

### 2. 재고 관리
   - 각 상품의 현재 재고를 관리합니다.
   - 사용자가 구매할 수 있는 최대 수량은 해당 상품의 남은 재고에 따라 제한됩니다.
   - 결제 시 구매한 상품의 수량만큼 재고를 차감하고, 시스템에서 최신 재고 상태를 유지합니다.
   - 재고가 부족한 상품을 구매하려는 경우, 재고 부족 알림을 띄웁니다.
<br>

### 3. 프로모션 할인
   - 프로모션 기간이 설정되어 있으며, 해당 기간 내에만 프로모션을 적용할 수 있습니다.
   - 프로모션은 N개 구매 시 1개 무료 증정(Buy N Get 1 Free) 형태로 제공됩니다.
     - 예: "1+1" 또는 "2+1" 프로모션
   - 프로모션 재고가 있다면 프로모션이 먼저 적용되며, 프로모션 재고가 부족할 경우에는 일반 재고를 사용합니다.
   - 프로모션 혜택을 받기 위한 최소 구매 수량을 알림으로 안내합니다.
   - 프로모션 재고가 부족하여 일부 상품에 대해서는 정가 결제가 이루어지게 됩니다.
   - 프로모션 재고가 없는 경우에는 고객이 일반 가격으로만 구매할 수 있도록 안내합니다.
<br>

### 4. 멤버십 할인
   - 멤버십 회원인 경우, 프로모션이 적용된 금액을 제외한 금액에 대해 30% 할인을 제공합니다.
   - 멤버십 할인의 최대 한도는 8,000원입다.
   - 멤버십 할인은 프로모션 할인 후 금액에 적용됩니다.
<br>

### 5. 최종 결제 금액 계산
   - 총 구매액은 각 상품의 가격과 수량을 곱한 값으로 계산됩니다.
   - 프로모션 할인: 프로모션에 의해 제공된 무료 상품을 반영하여 총 구매액에서 할인된 금액을 계산합니다.
   - 멤버십 할인: 프로모션 할인이 적용된 후 남은 금액에 대해 30%를 할인하며, 할인된 금액이 8,000원을 초과하지 않도록 합니다.
   - 최종 결제 금액을 계산한 후, 이 금액을 고객에게 안내합니다.
<br>

### 6. 영수증 출력
   - 구매 상품 내역 : 구매한 상품명, 수량, 가격을 나열합니다.
   - 증정 상품 내역 : 프로모션에 따라 무료로 제공된 상품의 목록을 출력합니다.
   - 금액 정보 : 총 구매액: 총 구매한 상품의 금액 합산합니다.
   - 행사할인: 프로모션 할인으로 인한 금액 차감합니다.
   - 멤버십 할인: 멤버십 할인이 적용된 금액 차감합니다.
   - 최종 결제 금액: 최종적으로 고객이 결제할 금액입니다.
   - 영수증 구성:
     - 각 항목을 보기 쉽게 정렬하여 출력합니다.
     - 금액 항목은 단위와 구분 기호를 명확히 하여 출력합니다.
<br>

### 7. 추가 구매 및 종료
   - 영수증 출력 후, 사용자가 추가 구매를 진행할지, 프로그램 종료를 선택할 수 있도록 합니다.
   - 추가 구매 시 기존 구매 내역과 함께 새로운 상품을 추가하여 최종 결제 금액을 다시 계산합니다.
   - 종료 시 프로그램을 종료하고, 최종 결제 금액과 영수증을 출력합니다.
<br>

### 8. 예외 처리
   - 잘못된 값을 입력할 경우, IllegalArgumentException을 발생시켜, "[ERROR]" 메시지로 오류를 안내하고 다시 입력받습니다.
   - 재고가 부족할 경우, IllegalStateException을 발생시켜 재고 부족 메시지를 출력하고 다시 입력받습니다.
   - 잘못된 날짜 형식이나 잘못된 할인 적용 범위 등에 대한 예외 처리를 명확하게 합니다.
<br>
