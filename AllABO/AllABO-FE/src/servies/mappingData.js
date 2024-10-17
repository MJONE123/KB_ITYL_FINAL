export const checkCardMapping = data => {
  const mappingData = [];

  for (let d of data) {
    mappingData.push({
      id: d.checkCardId,
      productId: d.productId,
      name: d.checkCardName,
      content: d.checkCardContent,
      data: {
        카테고리: d.checkCardCategory,
      },
      url: {
        imageUrl: d.checkCardImageUrl,
        pageUrl: d.checkCardDetailUrl,
      },
      isFavorite: false
    });
  }

  return mappingData;
};

export const creditCardMapping = data => {
  const mappingData = [];

  for (let d of data) {
    mappingData.push({
      id: d.creditCardId,
      productId: d.productId,
      name: d.creditCardName,
      content: d.creditCardContent,
      data: {
        카테고리: d.creditCardCategory,
        연회비: d.creditCardAnnualFee.toLocaleString(),
      },
      url: {
        imageUrl: d.creditCardImageUrl,
        pageUrl: d.creditCardDetailUrl,
      },
      isFavorite: false
    });
  }

  return mappingData;
};

export const loanMapping = data => {
  const mappingData = [];

  for (let d of data) {
    mappingData.push({
      id: d.loanId,
      productId: d.productId,
      name: d.loanName,
      content: d.loanContent,
      data: {
        "대출 금액": d.loanAmount,
        "개설 채널": d.loanChannel,
        조건: d.loanKeyword,
      },
      url: {
        imageUrl: "/images/common/money.png",
        pageUrl: d.loanUrl,
      },
      isFavorite: false
    });
  }

  return mappingData;
};

export const depositMapping = data => {
  const mappingData = [];

  for (let d of data) {
    mappingData.push({
      id: d.depositId,
      productId: d.productId,
      name: d.depositName,
      content: d.depositContent,
      data: {
        "기간 및 이율": d.depositInterestRate,
        "최소 예치금": d.depositMinAmount.toLocaleString(),
      },
      url: {
        imageUrl: d.depositCategory == "예금" ? "/images/common/money.png" : "/images/common/bank.png",
        pageUrl: d.depositUrl,
      },
      isFavorite: false
    });
  }

  return mappingData;
};

export const insuranceMapping = data => {
  const mappingData = [];

  for (let d of data) {
    mappingData.push({
      id: d.insuranceId,
      productId: d.productId,
      name: d.insuranceName,
      content: "KB 보험",
      data: {
        "보험 종류" : d.insuranceType
      },
      url: {
        imageUrl: "/images/common/bank.png",
        pageUrl: d.insuranceUrl,
      },
      isFavorite: false
    });
  }

  return mappingData;
};
