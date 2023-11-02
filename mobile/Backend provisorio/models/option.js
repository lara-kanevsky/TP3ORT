
class Option {
  constructor(type, optionId, label, value, nextScreenNavigationId, nextDefaultScreenNavigationId, screenId, measureUnit) {
    this.type = type;
    this.optionId = optionId;
    this.label = label;
    this.value = value;
    this.nextScreenNavigationId = nextScreenNavigationId;
    this.nextDefaultScreenNavigationId = nextDefaultScreenNavigationId;
    this.screenId = screenId;
    this.measureUnit = measureUnit;
  }
}

module.exports = {
  Option
};
