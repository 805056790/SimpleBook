package graduation.hnust.simplebook.view.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.dto.UserDto;
import graduation.hnust.simplebook.model.Book;
import graduation.hnust.simplebook.model.User;
import graduation.hnust.simplebook.service.BookWriteService;
import graduation.hnust.simplebook.service.impl.BookWriteServiceImpl;
import graduation.hnust.simplebook.util.ToastUtil;

/**
 * @Author : panxin109@gmail.com
 * @Date : 6:11 PM 5/10/16
 */
public class BudgetDialog extends Activity implements View.OnClickListener {

    private TextView txtPositive;
    private TextView txtNegative;
    private EditText editBudget;
    private Book book;
    private UserDto user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置布局内容
        setContentView(R.layout.activity_dialog_budget);
        this.setFinishOnTouchOutside(true);

        this.book = (Book) getIntent().getSerializableExtra("book");
        this.user = (UserDto) getIntent().getSerializableExtra("user");

        txtPositive = (TextView) findViewById(R.id.txt_dialog_budget_yes);
        txtNegative = (TextView) findViewById(R.id.txt_dialog_budget_no);
        editBudget = (EditText) findViewById(R.id.edit_budget);

        txtPositive.setOnClickListener(this);
        txtNegative.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_dialog_budget_yes:
                updateBudget();
                break;
            case R.id.txt_dialog_budget_no:
                txtNegative.setBackgroundResource(R.color.dialogPress);
                this.finish();
                break;
            default:
                break;
        }
    }

    /**
     * 更新
     */
    private void updateBudget() {
        try {
            String value = editBudget.getText().toString();
            Integer budget = Integer.parseInt(value);
            if (book == null) {
                book = new Book();
            }
            if (user == null) {
                book.setUserId(0L);
            }else {
                book.setUserId(user.getUser().getId());
            }
            book.setBudget(budget);
            BookWriteService service = new BookWriteServiceImpl(this.getApplicationContext());
            if (book.getId() == null) {
                service.create(book);
            }else {
                service.update(book);
            }
            ToastUtil.show(BudgetDialog.this, "修改完成!");
            this.finish();
        }catch (NumberFormatException e) {
            ToastUtil.show(BudgetDialog.this, "请填写数字!");
        }catch (Exception e) {
            ToastUtil.show(BudgetDialog.this, "修改失败,请重试!");
            e.printStackTrace();
            this.finish();
        }
    }
}
