import java.math.BigDecimal;
import java.util.ArrayList;

class PascalsTriangleC {
	private ArrayList<BigDecimal> row;
	private boolean Max;

	void calcNextRow() {
		if (!this.Max) {
			if (this.row == null) {
				this.row = new ArrayList<>();
				this.row.add(BigDecimal.ONE);
			} else {
				ArrayList<BigDecimal> nextRow = new ArrayList<>();
				for (int i = 0; i < this.row.size() + 1; ++i) {
					if (i == 0) {
						nextRow.add(i, this.row.get(i));
					}
					if (i == this.row.size()) {
						nextRow.add(i, this.row.get(i - 1));
					}
					if (i != 0 && i != this.row.size()) {
						nextRow.add(i, this.row.get(i - 1).add(this.row.get(i)));
					}
				}
				this.row = nextRow;
			}
			for (BigDecimal Pascal : this.row) {
				if (Pascal.compareTo(BigDecimal.ONE) < 0) {
					this.Max = true;
				}
			}
		}
	}

	ArrayList<BigDecimal> getRow() {
		return !this.Max ? this.row : null;
	}
}