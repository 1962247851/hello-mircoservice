export const tableOption = {
  "tabs": false,
  "column": [{
    "prop": "type",
    "span": 24,
    "type": "input",
    "label": "文章分类",
    "rules": [{"message": "文章分类必须填写", "required": true}],
    "display": true,
    "required": true
  }, {
    "prop": "title",
    "span": 24,
    "type": "input",
    "label": "标题",
    "rules": [{"message": "标题必须填写", "required": true}],
    "display": true,
    "prepend": "",
    "required": true,
    "maxlength": 50,
    "placeholder": "",
    "showWordLimit": true
  }, {
    "prop": "imgUrl",
    "span": 24,
    "type": "input",
    "label": "封面地址",
    "rules": [],
    "display": true,
    "maxlength": 500,
    "showWordLimit": true
  }, {
    "prop": "articleAbstract",
    "span": 24,
    "type": "textarea",
    "label": "文章摘要",
    "rules": [],
    "display": true
  }, {
    "prop": "content",
    "span": 24,
    "type": "textarea",
    "label": "文章内容",
    "rules": [{"message": "文章内容必须填写", "required": true}],
    "display": true,
    "required": true
  }, {
    "prop": "sort",
    "span": 12,
    "type": "number",
    "label": "文章排序",
    "display": true,
    "controls": true,
    "controlsPosition": ""
  }, {
    "prop": "delFlag",
    "span": 12,
    "type": "radio",
    "label": "是否删除",
    "props": {"label": "label", "value": "value"},
    "rules": [],
    "value": "false",
    "dicData": [{"label": "是", "value": "true"}, {"label": "否", "value": "false"}],
    "display": true
  }, {
    "prop": "original",
    "span": 12,
    "type": "radio",
    "label": "是否原创",
    "props": {"label": "label", "value": "value"},
    "rules": [],
    "value": "true",
    "dicData": [{"label": "是", "value": "true"}, {"label": "否", "value": "false"}],
    "display": true,
    "required": false
  }, {
    "prop": "canComment",
    "span": 12,
    "type": "radio",
    "label": "是否可以评论",
    "props": {"label": "label", "value": "value"},
    "rules": [],
    "value": "true",
    "dicData": [{"label": "是", "value": "true"}, {"label": "否", "value": "false"}],
    "display": true
  }, {
    "prop": "shown",
    "span": 12,
    "type": "radio",
    "label": "是否展示",
    "props": {"label": "label", "value": "value"},
    "value": "true",
    "dicData": [{"label": "是", "value": "true"}, {"label": "否", "value": "false"}],
    "display": true
  }, {
    "prop": "canReward",
    "span": 12,
    "type": "radio",
    "label": "是否可以打赏",
    "props": {"label": "label", "value": "value"},
    "rules": [],
    "value": "true",
    "dicData": [{"label": "是", "value": "true"}, {"label": "否", "value": "false"}],
    "display": true
  }, {
    "prop": "createBy",
    "span": 12,
    "type": "input",
    "label": "创建者",
    "display": true,
    "readonly": true
  }, {
    "prop": "createTime",
    "span": 12,
    "type": "datetime",
    "label": "创建时间",
    "format": "yyyy-MM-dd HH:mm:ss",
    "display": true,
    "disabled": true,
    "valueFormat": "yyyy-MM-dd HH:mm:ss"
  }, {
    "prop": "updateBy",
    "span": 12,
    "type": "input",
    "label": "更新者",
    "display": true,
    "readonly": true
  }, {
    "prop": "updateTime",
    "span": 12,
    "type": "datetime",
    "label": "更新时间",
    "format": "yyyy-MM-dd HH:mm:ss",
    "display": true,
    "disabled": true,
    "valueFormat": "yyyy-MM-dd HH:mm:ss"
  }],
  "detail": false,
  "gutter": 0,
  "menuBtn": true,
  "emptyBtn": true,
  "emptyText": "清空",
  "submitBtn": true,
  "labelWidth": 120,
  "submitText": "提交",
  "labelSuffix": "：",
  "menuPosition": "center",
  "labelPosition": "left"
}
