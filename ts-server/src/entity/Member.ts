import { Entity, PrimaryColumn, Column, OneToOne, JoinColumn, ManyToOne, ManyToMany, JoinTable, OneToMany } from 'typeorm';
import { Organization } from './Organization';
import { Category } from './Category';
import { Board } from './Board';
import { Comment } from './Comment';

@Entity()
export class Member {
    @PrimaryColumn({ type: 'char', length: 50 })
    id!: string;

    @ManyToOne(() => Organization, organization => organization.member)
    organization!: Organization;

    @ManyToMany(() => Category, category => category.member)
    @JoinTable()
    category!: Category[];

    @OneToMany(() => Board, board => board.writer)
    board!: Board[];

    @OneToMany(() => Comment, comment => comment.writer)
    comment!: Comment[];

    @Column({ type: 'varchar', length: 50 })
    name!: string;

    @Column({ type: 'varchar', length: 500 })
    password!: string;

    @Column({ type: 'varchar', length: 300, nullable: true })
    image!: string;
}
