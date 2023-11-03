
class Screen {
  constructor(type, screenId, navigationId, formId, completePercentage, isFinal, category, question, options) {
    this.type = type;
    this.screenId = screenId;
    this.navigationId = navigationId;
    this.formId = formId;
    this.completePercentage = completePercentage;
    this.isFinal = isFinal;
    this.category = category;
    this.question = question;
    this.options = options;
  }
}

module.exports = {
  Screen,
};
