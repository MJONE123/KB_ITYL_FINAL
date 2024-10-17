export const insuranceSurvey = [
  {
    id: 0,
    text: '연령대를 선택해주세요.',
    options: ['10대', '20대', '30대', '40대', '50대', '60대 이상'],
    nextQuestion: 1,
  },
  {
    id: 1,
    text: '찾으시는 유형을 선택해주세요.',
    options: [
      '자동차보험',
      '실손보험',
      '건강보험',
      '종신보험',
      '연금보험',
      '기타',
    ],
    nextQuestion: 2,
  },
  {
    id: 2,
    text: '가입 방식을 선택해주세요.',
    options: ['온라인(다이렉트)', '보험설계사 방문'],
    nextQuestion: 3,
  },
  {
    id: 3,
    text: '과거 병력 여부를 선택해주세요. (암, 뇌졸중, 심근경색 등)',
    options: ['과거 병력이 있음', '과거 병력이 없음'],
    nextQuestion: null,
  },
];

export const cardSurvey = [
  {
    id: 0,
    text: '찾으시는 카드의 종류를 선택해주세요.',
    options: ['신용카드', '체크카드'],
    nextQuestion: (option) => (option === '신용카드' ? 1 : 2),
  },
  {
    id: 1,
    text: '희망하는 연회비를 선택해주세요.',
    options: [
      '없음',
      '1만원 이하',
      '1만원 ~ 3만원',
      '3만원 ~ 10만원',
      '10만원 이상',
    ],
    nextQuestion: 3,
  },
  {
    id: 2,
    text: '연령대를 선택해주세요.',
    options: ['10대', '20대', '30대', '40대', '50대', '60대 이상'],
    type: '체크카드',
    nextQuestion: 3,
  },
  {
    id: 3,
    text: '원하는 형태의 혜택을 선택해주세요.',
    options: ['할인형', '적립형'],
    nextQuestion: 4,
  },
  {
    id: 4,
    text: '주로 소비하는 유형을 선택해주세요.',
    options: [
      '여행',
      '쇼핑',
      '렌탈',
      '주유/충전',
      '의료',
      '통신',
      '교육',
      '공공/정부지원',
    ],
    nextQuestion: null,
  },
];

export const loanSurvey = [
  {
    id: 0,
    text: '대출 방식을 선택해주세요.',
    options: ['KB스타뱅킹(온라인)', '영업점 방문(오프라인)'],
    nextQuestion: 1,
  },
  {
    id: 1,
    text: '원하는 대출 유형을 선택해주세요.',
    options: [
      '신용대출',
      '담보대출',
      '전월세/반환보증대출',
      '자동차대출',
      '주택도시기금대출',
    ],
    nextQuestion: (option) => {
      switch (option) {
        case '신용대출':
          return 2;
        case '담보대출':
          return 3;
        case '전월세/반환보증대출':
          return 4;
        case '자동차대출':
          return 5;
        case '주택도시기금대출':
          return 6;
      }
    },
  },
  {
    id: 2,
    text: '직업을 선택해주세요.',
    options: ['직장인', '공무원/교직원', '전문직', '군인', '기타'],
    nextQuestion: null,
  },
  {
    id: 3,
    text: '대출 목적을 선택해주세요.',
    options: ['생활비', '급전', '기타'],
    nextQuestion: null,
  },
  {
    id: 4,
    text: '해당되는 유형을 선택해주세요.',
    options: ['신혼', '다자녀', '전세사기피해자', '청년', '군인', '기타'],
    nextQuestion: null,
  },
  {
    id: 5,
    text: '어떤 차를 살 예정인가요?',
    options: ['신차', '중고차', '둘 다'],
    nextQuestion: null,
  },
  {
    id: 6,
    text: '해당되는 유형을 선택해주세요.',
    options: [
      '무주택',
      '청년',
      '중소기업 재직',
      '신혼',
      '2년 이내 출산경험',
      '기타',
    ],
    nextQuestion: null,
  },
];

export const depositsSurvey = [
  {
    id: 0,
    text: '나는 저축을 할 때',
    options: [
      '한 번에 넣어 목돈을 보관하고 싶다.',
      '조금씩 넣어 목돈을 만들고 싶다.',
    ],
    nextQuestion: 1,
  },
  {
    id: 1,
    text: '나는 저축 가능한 현금 자산이',
    options: ['100만원 이하다.', '100만원 이상이다.'],
    nextQuestion: 2,
  },
  {
    id: 2,
    text: '나는 상품에 가입할 때',
    options: [
      '온라인으로만 가입하고 싶다.',
      '금리가 괜찮다면 영업점 방문도 상관없다.',
    ],
    nextQuestion: 3,
  },
  {
    id: 3,
    text: '상품 가입 기간은',
    options: [
      '6개월 이하였으면 좋겠다.',
      '6개월 ~ 1년 정도가 좋다.',
      '1년 이상도 상관없다.',
    ],
    nextQuestion: 4,
  },
  {
    id: 4,
    text: '나의 저축 성향은',
    options: [
      '조금 번거롭더라도 우대 이율을 챙기는 편이다.',
      '확정된 이자율만 받아도 된다.',
    ],
    nextQuestion: null,
  },
];
