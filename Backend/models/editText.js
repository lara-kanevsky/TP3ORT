
const { Option } = require("./option");

class EditText extends Option {
  constructor(type, optionId, label, value, nextScreenNavigationId, nextDefaultScreenNavigationId, screenId, measureUnit, userInput) {
    super(type, optionId, label, value, nextScreenNavigationId, nextDefaultScreenNavigationId, screenId, measureUnit)
    this.userInput = userInput;
  }
}

module.exports = {
  EditText
};
